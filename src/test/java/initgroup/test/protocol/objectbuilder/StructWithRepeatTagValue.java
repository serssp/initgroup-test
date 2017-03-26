package initgroup.test.protocol.objectbuilder;

import initgroup.test.protocol.annotation.Tag;

class StructWithRepeatTagValue {
	private int value1;
	private int value2;

	@Tag(1)
	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	@Tag(1)
	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}
}
