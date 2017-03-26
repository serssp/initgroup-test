package initgroup.test.protocol.impl;

import initgroup.test.protocol.Decoder;
import initgroup.test.protocol.impl.exception.TagDecoderAlreadyRegisterException;
import initgroup.test.protocol.impl.exception.TagDecoderNotRegistegerException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertSame;

@Test
public class ProtocolImplTest {

	private ProtocolImpl protocol;

	@BeforeMethod
	public void setUp() {
		protocol = new ProtocolImpl();
	}

	public void getDecoder_shouldReturnRegisterDecoder() {
		int tag = 123;
		Decoder decoder = mock(Decoder.class);
		protocol.registerDecoder(tag, decoder);

		assertSame(protocol.getDecoder(tag), decoder);
	}

	@Test(expectedExceptions = TagDecoderNotRegistegerException.class)
	public void getDecoder_shouldThrowException_whenDecoderNotRegistered() {
		protocol.getDecoder(123);
	}


	@Test(expectedExceptions = TagDecoderAlreadyRegisterException.class)
	public void registerDecoder_shouldThrowException_whenDecoderRegisteredTwice() {
		int tag = 123;
		Decoder decoder = mock(Decoder.class);
		protocol.registerDecoder(tag, decoder);
		protocol.registerDecoder(tag, decoder);
	}
}