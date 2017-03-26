package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Decoder;
import initgroup.test.protocol.Protocol;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ListStructDecoder<T> extends AbstractTLVDecoder<T> implements Decoder<List<T>> {

	public ListStructDecoder(Protocol protocol, Class<T> clazz) {
		super(protocol, clazz);
	}

	@Override
	public List<T> decode(InputStream stream, int length) {
		AtomicInteger position = new AtomicInteger();
		List<T> result = new ArrayList<>();
		while (position.get() < length) {
			T value = decodeStruct(stream, position);
			result.add(value);
		}
		return result;
	}
}
