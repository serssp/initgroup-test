package initgroup.test.protocol.impl;

import initgroup.test.protocol.Decoder;
import initgroup.test.protocol.Protocol;
import initgroup.test.protocol.impl.exception.TagDecoderAlreadyRegisterException;
import initgroup.test.protocol.impl.exception.TagDecoderNotRegistegerException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtocolImpl implements Protocol {
	private Map<Integer, Decoder> decodes = new ConcurrentHashMap<>();

	@Override
	public void registerDecoder(int tag, Decoder decoder) {
		Decoder old = decodes.put(tag, decoder);
		if (old != null) {
			throw new TagDecoderAlreadyRegisterException(tag, old, decoder);
		}
	}

	@Override
	public Decoder getDecoder(int tag) {
		Decoder decoder = decodes.get(tag);
		if (decoder == null) {
			throw new TagDecoderNotRegistegerException(tag);
		}
		return decoder;
	}
}
