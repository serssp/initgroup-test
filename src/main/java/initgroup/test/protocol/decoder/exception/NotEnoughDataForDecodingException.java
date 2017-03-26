package initgroup.test.protocol.decoder.exception;

/**
 * Недостаточно данных в буфере при декодировании.
 * Например, при невозможности считывания из буфера запрошенной длинны
 */
public class NotEnoughDataForDecodingException extends DecodeException {

	public NotEnoughDataForDecodingException() {
	}

	public NotEnoughDataForDecodingException(Throwable cause) {
		super(cause);
	}
}
