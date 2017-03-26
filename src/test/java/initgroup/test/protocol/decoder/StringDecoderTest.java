package initgroup.test.protocol.decoder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.testng.Assert.assertEquals;

@Test
public class StringDecoderTest {

	private StringDecoder decoder;

	@BeforeMethod
	public void setUp() {
		decoder = new StringDecoder(100);
	}

	public void decoder() throws IOException {
		Charset charset = Charset.forName("CP866");

		String source = "Мир! Труд! Май!";
		ByteArrayInputStream stream = new ByteArrayInputStream(source.getBytes(charset));

		String result = decoder.decode(stream, source.length());

		assertEquals(result, source);
	}
}