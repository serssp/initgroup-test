package initgroup.test.protocol.objectbuilder;

import initgroup.test.protocol.objectbuilder.exception.StructIntrospectionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

@Test
public class StructBuilderTest {

	@DataProvider(name = "wrong_structs")
	public Object[][] wrong_structs() {
		return new Object[][]{
				{StructWithoutTag.class},
				{StructWithoutSetter.class},
				{StructWithRepeatTagValue.class}
		};
	}

	@Test(dataProvider = "wrong_structs", expectedExceptions = StructIntrospectionException.class)
	public void validate(Class<?> clazz) {
		new StructBuilder<>(clazz);
	}

	public void build_whenSimpleStruct() {
		int intValue = 123;
		String stringValue = "Мир!Труд!Май!";

		StructBuilder<Struct> builder = new StructBuilder<>(Struct.class);
		Struct struct = builder.build(new HashMap<Integer, Object>() {{
			put(1, intValue);
			put(2, stringValue);
		}});

		assertEquals(struct.getIntValue(), intValue);
		assertEquals(struct.getStringValue(), stringValue);
	}

//	public void build_whenList() {
//		StructBuilder<StructWithList> builder = new StructBuilder<>(StructWithList.class);
//
//		StructWithList struct = builder.build(new HashMap<Ingege>() {{
//			put(1, Arrays.asList("A","B"));
//		}});
//
//		assertThat(struct.getData()).containsExactly("A", "B");
//
//
//	}
}