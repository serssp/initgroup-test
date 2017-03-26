package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Decoder;
import initgroup.test.protocol.Protocol;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class TLVDecoder<T> extends AbstractTLVDecoder<T> implements Decoder<T> {

	public TLVDecoder(Protocol protocol, Class<T> clazz) {
		super(protocol, clazz);
	}

	public T decode(InputStream stream, int length) {
		return decodeStruct(stream, new AtomicInteger());
	}
}
