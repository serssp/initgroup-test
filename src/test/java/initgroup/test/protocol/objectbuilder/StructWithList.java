package initgroup.test.protocol.objectbuilder;

import initgroup.test.protocol.annotation.Tag;

import java.util.List;

class StructWithList {
	private List<String> data;

	@Tag(1)
	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
}
