package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Decoder;

public abstract class AbstractDecoder<T> implements Decoder<T> {
	protected StreamReadHelper reader = new StreamReadHelper();
}
