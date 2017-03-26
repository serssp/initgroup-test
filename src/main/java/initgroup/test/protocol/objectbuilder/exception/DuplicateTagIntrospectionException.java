package initgroup.test.protocol.objectbuilder.exception;

public class DuplicateTagIntrospectionException extends StructIntrospectionException {

	public DuplicateTagIntrospectionException(int tag) {
		super("Существуют два свойства с одинаковым тегом: " + tag);
	}
}
