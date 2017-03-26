package initgroup.test.protocol.impl.exception;

public class TagDecoderNotRegistegerException extends RuntimeException {
	public TagDecoderNotRegistegerException(int tag) {
		super("Tag: " + tag);
	}
}
