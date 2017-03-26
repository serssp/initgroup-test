package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Protocol;
import initgroup.test.protocol.annotation.Tag;
import initgroup.test.protocol.decoder.exception.NotEnoughDataForDecodingException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.when;

@Test
public class TLVDecoderTest {

	@Mock
	private Protocol protocol;

	private TLVDecoder<Struct> decoder;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		StringDecoder stringDecoder = new StringDecoder();
		when(protocol.getDecoder(1)).thenReturn(stringDecoder);
		when(protocol.getDecoder(2)).thenReturn(stringDecoder);

		decoder = new TLVDecoder<>(protocol, Struct.class);
	}

	public void decode_shouldDecode_whenSourceDataCorrect() {
		byte[] bytes = {
				1, 0, 1, 0, 'A',
				2, 0, 3, 0, 'B', 'B', 'B'
		};
		ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
		decoder.decode(stream, bytes.length);
	}

	@Test(expectedExceptions = NotEnoughDataForDecodingException.class)
	public void decode_shouldThrowException_whenTLVwrong() {
		byte[] bytes = {1, 0, 1};
		ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
		decoder.decode(stream, bytes.length);
	}

	public static class Struct {
		String a;
		String b;

		@Tag(1)
		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		@Tag(2)
		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}
}