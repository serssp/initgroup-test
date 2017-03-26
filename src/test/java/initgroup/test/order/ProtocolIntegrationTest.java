package initgroup.test.order;

import initgroup.test.json.JsonService;
import initgroup.test.order.struct.Order;
import initgroup.test.order.struct.OrderItem;
import initgroup.test.runner.OrderReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;

@Test
public class ProtocolIntegrationTest {

	private OrderReader converter;

	@BeforeMethod
	public void setUp() {
		converter = new OrderReader();
	}

	public void decode() throws ParseException {
		OrderItem expectedItem = new OrderItem("Дырокол",
				new BigDecimal(20000),
				new BigDecimal(2),
				new BigDecimal(40000));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = format.parse("2016-01-10T10:30:00");
		Order expecedOrder = new Order(date,
				new BigDecimal(160004),
				"ООО Ромашка",
				Collections.singletonList(expectedItem));

		Order order = converter.read(getStream());

		assertEquals(order, expecedOrder);
	}

	public void serialize() throws IOException {
		Order order = converter.read(getStream());
		JsonService jsonService = new JsonService();
		String json = jsonService.toJson(order);

		assertEqualsWithoutBlank(json, getExpectedJson());
	}

	private void assertEqualsWithoutBlank(String actual, String expected) {
		assertEquals(actual.replaceAll("\\s",""), expected.replaceAll("\\s",""));
	}

	private ByteArrayInputStream getStream() {
		String source = "01 00 04 00 A8 32 92 56 02 00 03 00 04 71 02 03 " +
				"00 0B 00 8E 8E 8E 20 90 AE AC A0 E8 AA A0 04 00 " +
				"1D 00 0B 00 07 00 84 EB E0 AE AA AE AB 0C 00 02 " +
				"00 20 4E 0D 00 02 00 00 02 0E 00 02 00 40 9C";


		String[] split = source.split(" ");
		byte[] bytes = new byte[split.length];
		for (int i = 0; i < split.length; i++) {
			bytes[i] = Integer.valueOf(split[i], 16).byteValue();
		}

		return new ByteArrayInputStream(bytes);
	}

	private String getExpectedJson() {
		return "{\n" +
				"  \"dateTime\": \"2016-01-10T10:30:00\",\n" +
				"  \"orderNumber\": 160004,\n" +
				"  \"customerName\": \"ООО Ромашка\",\n" +
				"  \"items\": [\n" +
				"    {\n" +
				"      \"name\": \"Дырокол\",\n" +
				"      \"price\": 20000,\n" +
				"      \"quantity\": 2,\n" +
				"      \"sum\": 40000\n" +
				"    }\n" +
				"  ]\n" +
				"}";
	}
}