package initgroup.test.protocol.impl.exception;

import initgroup.test.protocol.Decoder;

public class TagDecoderAlreadyRegisterException extends RuntimeException {
	public TagDecoderAlreadyRegisterException(int tag, Decoder oldDecoder, Decoder newDecoder) {
		super("Tag: " + tag + " existing: " + oldDecoder.getClass() + " new: " + newDecoder.getClass());
	}
}
