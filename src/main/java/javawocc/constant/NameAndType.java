package javawocc.constant;

public class NameAndType extends Constant {
	private UTF8Constant name;
	private UTF8Constant type;

	public NameAndType(UTF8Constant name, UTF8Constant type) {
		super();
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("0c"); // type
		sb.append(String.format("%04x", name.getIndex()));
		sb.append(String.format("%04x", type.getIndex()));
		return sb.toString();
	}

}
