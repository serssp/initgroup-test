package initgroup.test.protocol.objectbuilder.exception;

import java.beans.PropertyDescriptor;

public class TagAnnotationNotFoundException extends StructIntrospectionException {
	public TagAnnotationNotFoundException(PropertyDescriptor descriptor) {
		super("Метод " + descriptor.getReadMethod() + " без аннотации Tag. На всех get методах должна быть эта аннотация");
	}
}
