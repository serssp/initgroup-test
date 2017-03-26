package initgroup.test.protocol.decoder.exception;

/**
 * Базовый класс ошибкок при декодировании
 */
public class DecodeException extends RuntimeException {

	public DecodeException() {
	}

	public DecodeException(Throwable cause) {
		super(cause);
	}

	public DecodeException(String message) {
		super(message);
	}
}
