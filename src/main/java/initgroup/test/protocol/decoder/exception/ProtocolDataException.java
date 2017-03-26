package initgroup.test.protocol.decoder.exception;

/**
 * Возникает при ошибках раскодировании пакета в следствии неверных исходных данных.
 * Обычно причина в ошибках при кодировании
 */
public class ProtocolDataException extends DecodeException {
	public ProtocolDataException() {
	}

	public ProtocolDataException(String message) {
		super(message);
	}
}
