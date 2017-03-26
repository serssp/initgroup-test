package initgroup.test.protocol.decoder;

import initgroup.test.protocol.Protocol;
import initgroup.test.protocol.annotation.Tag;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Test
public class ListDecoderTest {
	@Mock
	private Protocol protocol;

	private ListStructDecoder<Struct> decoder;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		StringDecoder stringDecoder = new StringDecoder();
		when(protocol.getDecoder(1)).thenReturn(stringDecoder);

		decoder = new ListStructDecoder<>(protocol, Struct.class);
	}

	public void decode_shouldDecode_whenSourceDataCorrect() {
		byte[] bytes = {
				1, 0, 1, 0, 'A',
				1, 0, 3, 0, 'B', 'B', 'B'
		};
		ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
		List<Struct> value = decoder.decode(stream, bytes.length);

		assertThat(value).containsExactly(new Struct("A"), new Struct("BBB") );
	}

	public static class Struct {
		private String string;

		public Struct() {
		}

		public Struct(String string) {
			this.string = string;
		}

		@Tag(1)
		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;

			if (o == null || getClass() != o.getClass()) return false;

			Struct struct = (Struct) o;

			return new EqualsBuilder()
					.append(string, struct.string)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder(17, 37)
					.append(string)
					.toHashCode();
		}
	}
}