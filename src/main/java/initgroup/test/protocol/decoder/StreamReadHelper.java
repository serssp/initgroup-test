package initgroup.test.protocol.decoder;

import initgroup.test.protocol.decoder.exception.NotEnoughDataForDecodingException;
import initgroup.test.protocol.decoder.exception.ProtocolDataException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import static org.apache.commons.lang3.ArrayUtils.reverse;

public class StreamReadHelper {
	public byte[] read(InputStream stream, int length) {
		try {
			if (length < 0) {
				throw new ProtocolDataException();
			}

			byte[] val = new byte[length];
			int count = stream.read(val);
			if (count < 0) {
				throw new NotEnoughDataForDecodingException();
			}
			return val;
		} catch (IOException e) {
			throw new NotEnoughDataForDecodingException(e);
		}
	}

	/**
	 * Возвращает безнаковое BigInteger с порядком байт little-endian
	 */
	public BigInteger readUnsigned(InputStream stream, int length) {
		byte[] bytes = read(stream, length);

		reverse(bytes);

		BigInteger value = new BigInteger(bytes);
		if (value.compareTo(BigInteger.ZERO) < 0) {
			value = value.add(BigInteger.ONE.shiftLeft(length << 3));
		}
		return value;
	}
}
