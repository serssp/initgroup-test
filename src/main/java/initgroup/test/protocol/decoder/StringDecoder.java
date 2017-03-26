package initgroup.test.protocol.decoder;

import initgroup.test.protocol.decoder.exception.PacketLengthTooLongException;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Декодер строковых значений
 */
public class StringDecoder extends AbstractDecoder<String> {

	private Charset charset;
	private Integer maxLength = null;

	/**
	 * Создает декодер с кодовой таблицей cp866
	 */
	public StringDecoder() {
		this.charset = Charset.forName("CP866");
	}

	public StringDecoder(int maxLength) {
		if (maxLength < 0) {
			throw new ArithmeticException();
		}

		this.charset = Charset.forName("CP866");
		this.maxLength = maxLength;
	}

	@Override
	public String decode(InputStream stream, int length) {
		if (maxLength != null && length > maxLength) {
			throw new PacketLengthTooLongException(length, maxLength);
		}
		byte[] values = reader.read(stream, length);
		return new String(values, charset);
	}
}
