package initgroup.test.protocol.decoder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.testng.Assert.assertEquals;

@Test
public class Uint32DecoderTest {

	private Uint32Decoder decoder;
	private BigInteger MAX_UNSIGNED_INT = BigInteger.ONE.shiftLeft(32).add(new BigInteger("-1"));

	@BeforeMethod
	public void setUp() {
		decoder = new Uint32Decoder();
	}

	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][]{
				{new byte[]{1, 0, 0, 0}, new BigDecimal("1")},
				{new byte[]{-1, -1, -1, -1}, new BigDecimal(MAX_UNSIGNED_INT)}
		};
	}

	@Test(dataProvider = "data")
	public void decode(byte[] val, BigDecimal expect) throws IOException {
		ByteArrayInputStream stream = new ByteArrayInputStream(val);
		BigDecimal result = decoder.decode(stream, val.length);
		assertEquals(result, expect);
	}
}