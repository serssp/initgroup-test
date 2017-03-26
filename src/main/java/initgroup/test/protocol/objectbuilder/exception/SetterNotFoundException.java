package initgroup.test.protocol.objectbuilder.exception;

import java.beans.PropertyDescriptor;

public class SetterNotFoundException extends StructIntrospectionException {
	public SetterNotFoundException(PropertyDescriptor descriptor) {
		super("Нет set медода в свойстве: " + descriptor);
	}
}
