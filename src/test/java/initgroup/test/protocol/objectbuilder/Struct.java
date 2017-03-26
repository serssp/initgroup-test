package initgroup.test.protocol.objectbuilder;

import initgroup.test.protocol.annotation.Tag;

class Struct {
	public int intValue;
	public String stringValue;

	@Tag(1)
	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	@Tag(2)
	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
}
