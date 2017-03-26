package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Decoder;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateDecoder implements Decoder<Date> {
	private Uint32Decoder uint32Decoder = new Uint32Decoder();

	@Override
	public Date decode(InputStream stream, int length) {
		BigDecimal secondsFrom1970 = uint32Decoder.decode(stream, length);
		return new Date(TimeUnit.SECONDS.toMillis(secondsFrom1970.longValue()));
	}
}
