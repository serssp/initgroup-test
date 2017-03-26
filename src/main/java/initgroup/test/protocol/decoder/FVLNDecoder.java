package initgroup.test.protocol.decoder;

import initgroup.test.protocol.decoder.exception.DecodeException;
import initgroup.test.protocol.decoder.exception.NotEnoughDataForDecodingException;
import initgroup.test.protocol.decoder.exception.PacketLengthTooLongException;
import initgroup.test.protocol.decoder.exception.ProtocolDataException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Декодер безнаковых чисел с фиксированной точкой. Порядок байт little-endian
 * Первый байт положение фиксированной точки
 * Примечание: В ТЗ упоминается плавающая точка IEEE754, хотя по смыслу больше походит на число с фиксированной точкой.
 */
public class FVLNDecoder extends AbstractDecoder<BigDecimal> {

	private int maxLength;

	public FVLNDecoder(int maxLength) {
		this.maxLength = maxLength;
	}

	public BigDecimal decode(InputStream stream, int length) {
		if (length > maxLength) {
			throw new PacketLengthTooLongException(length, maxLength);
		}

		try {
			if (length < 2) {
				throw new ProtocolDataException();
			}

			int scale = stream.read();
			if (scale < 0) {
				throw new NotEnoughDataForDecodingException();
			}

			BigInteger value = reader.readUnsigned(stream, length - 1);
			return new BigDecimal(value, scale);
		} catch (IOException e) {
			throw new DecodeException(e);
		}
	}
}
