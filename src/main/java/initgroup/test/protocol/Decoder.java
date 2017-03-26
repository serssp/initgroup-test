package initgroup.test.protocol;

import java.io.InputStream;

public interface Decoder<T> {
	T decode(InputStream stream, int length);
}
