package initgroup.test.protocol.decoder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

@Test
public class VLNDecoderTest {
	private VLNDecoder decoder;

	@BeforeMethod
	public void setUp() {
		decoder = new VLNDecoder(4);
	}

	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][]{
				{new byte[]{1, 0}, new BigDecimal("1")},
				{new byte[]{-1, -1}, new BigDecimal("65535")}
		};
	}

	@Test(dataProvider = "data")
	public void decode(byte[] val, BigDecimal expect) throws IOException {
		ByteArrayInputStream stream = new ByteArrayInputStream(val);
		BigDecimal result = decoder.decode(stream, val.length);
		assertEquals(result, expect);
	}
}