package initgroup.test.protocol.objectbuilder;

import initgroup.test.protocol.annotation.Tag;
import initgroup.test.protocol.objectbuilder.exception.BuildObjectException;
import initgroup.test.protocol.objectbuilder.exception.DuplicateTagIntrospectionException;
import initgroup.test.protocol.objectbuilder.exception.NotEnoughsTagBuildObjectException;
import initgroup.test.protocol.objectbuilder.exception.SetterNotFoundException;
import initgroup.test.protocol.objectbuilder.exception.StructIntrospectionException;
import initgroup.test.protocol.objectbuilder.exception.TagAnnotationNotFoundException;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Stream.of;

public class StructBuilder<T> {

	private Class<T> clazz;
	private Map<Integer, Method> setMethods = new HashMap<>();

	public StructBuilder(Class<T> clazz) {
		this.clazz = clazz;
		collectSetMethods(clazz);
	}

	private void collectSetMethods(Class<T> clazz) {
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector
					.getBeanInfo(clazz, Object.class)
					.getPropertyDescriptors();

			of(propertyDescriptors)
					.peek(this::validate)
					.forEach(this::collectSetters);
		} catch (IntrospectionException e) {
			throw new StructIntrospectionException(e);
		}
	}

	private void validate(PropertyDescriptor desc) {
		Tag tag = desc.getReadMethod().getAnnotation(Tag.class);
		if (tag == null) {
			throw new TagAnnotationNotFoundException(desc);
		}

		Method writeMethod = desc.getWriteMethod();
		if (writeMethod == null) {
			throw new SetterNotFoundException(desc);
		}

		if (setMethods.containsKey(tag.value())) {
			throw new DuplicateTagIntrospectionException(tag.value());
		}
	}

	private void collectSetters(PropertyDescriptor desc) {
		Tag tag = desc.getReadMethod().getAnnotation(Tag.class);
		Method writeMethod = desc.getWriteMethod();
		setMethods.put(tag.value(), writeMethod);
	}

	public T build(Map<Integer, Object> data) {
		if (data.size() != getFieldsCount()) {
			throw new NotEnoughsTagBuildObjectException();
		}

		try {
			T object = clazz.newInstance();
			setMethods.forEach((tag, method) -> set(object, method, data.get(tag)));
			return object;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BuildObjectException(e);
		}
	}

	private void set(T object, Method method, Object data) {
		if (data == null) {
			throw new NullPointerException();
		}
		try {
			method.invoke(object, data);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new BuildObjectException(e);
		}
	}

	public int getFieldsCount() {
		return setMethods.size();
	}
}
