package initgroup.test.protocol.decoder;

import initgroup.test.protocol.decoder.exception.ProtocolDataException;
import initgroup.test.protocol.decoder.exception.NotEnoughDataForDecodingException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

@Test
public class FVLNDecoderTest {

	private FVLNDecoder decoder;

	@BeforeMethod
	public void setUp() {
		decoder = new FVLNDecoder(8);
	}

	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][]{
				{new byte[]{1, 100}, new BigDecimal("10.0")},
				{new byte[]{1, 123}, new BigDecimal("12.3")},
				{new byte[]{1, 1, 2}, new BigDecimal("51.3")}        // (2 * 256 + 1) / 10
		};
	}

	@Test(dataProvider = "data")
	public void decode(byte[] val, BigDecimal expect) throws IOException {
		ByteArrayInputStream stream = new ByteArrayInputStream(val);
		BigDecimal result = decoder.decode(stream, val.length);
		assertEquals(result, expect);
	}

	@Test(expectedExceptions = ProtocolDataException.class)
	public void decode_shouldThrowException_whenLengthTooShort() throws IOException {
		InputStream stream = mock(InputStream.class);
		decoder.decode(stream, 1);
	}

	@Test(expectedExceptions = NotEnoughDataForDecodingException.class)
	public void decode_shouldThrowException_whenScale() throws IOException {
		decoder.decode(new ByteArrayInputStream(new byte[]{}), 2);
	}

	@Test(expectedExceptions = NotEnoughDataForDecodingException.class)
	public void decode_shouldThrowException_whenDataNotAvailable() throws IOException {
		decoder.decode(new ByteArrayInputStream(new byte[]{0}), 2);
	}
}