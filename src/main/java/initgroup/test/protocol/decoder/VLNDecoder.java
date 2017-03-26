package initgroup.test.protocol.decoder;

import initgroup.test.protocol.decoder.exception.PacketLengthTooLongException;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Декодер безнакового целого. Порядок байт little-endian
 */
public class VLNDecoder extends AbstractDecoder<BigDecimal> {

	private int maxLength;

	public VLNDecoder(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public BigDecimal decode(InputStream stream, int length) {
		if (length > maxLength) {
			throw new PacketLengthTooLongException(length, maxLength);
		}
		BigInteger value = reader.readUnsigned(stream, length);
		return new BigDecimal(value, 0);
	}
}
