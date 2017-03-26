package initgroup.test.protocol;

public interface Protocol {
	void registerDecoder(int tag, Decoder decoder);

	Decoder getDecoder(int tag);
}
