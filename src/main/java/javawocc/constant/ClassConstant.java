package javawocc.constant;

public class ClassConstant extends Constant {
	private UTF8Constant utf8;

	public ClassConstant(UTF8Constant utf8) {
		this.utf8 = utf8;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("07"); // type
		sb.append(String.format("%04x", this.utf8.getIndex()));
		return sb.toString();
	}

}
