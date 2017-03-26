package initgroup.test.protocol.decoder.exception;

public class PacketLengthTooLongException extends ProtocolDataException {
	public PacketLengthTooLongException(int currentLength, int maxLength) {
		super("CurrentLength: " + currentLength + ", maxDecoderLength: " + maxLength);
	}
}
