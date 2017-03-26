package initgroup.test.protocol.decoder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Date;

import static org.testng.Assert.assertEquals;

@Test
public class DateDecoderTest {

	private DateDecoder decoder;

	@BeforeMethod
	public void setUp() {
		decoder = new DateDecoder();
	}

	public void decode() {
		long maxTime = ((1L << 32) - 1) * 1000;

		ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{-1, -1, -1, -1});
		Date result = decoder.decode(stream, 4);
		assertEquals(result, new Date(maxTime));
	}
}
