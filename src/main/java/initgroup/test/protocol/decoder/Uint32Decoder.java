package initgroup.test.protocol.decoder;

import initgroup.test.protocol.decoder.exception.ProtocolDataException;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 * Декодер 32 битных целых безнаковых чисел. Порядок байт little-endian
 */
public class Uint32Decoder extends VLNDecoder {
	private final static int PACKET_SIZE = 4;

	public Uint32Decoder() {
		super(PACKET_SIZE);
	}

	@Override
	public BigDecimal decode(InputStream stream, int length) {
		if (length != PACKET_SIZE) {
			throw new ProtocolDataException();
		}
		return super.decode(stream, length);
	}
}
