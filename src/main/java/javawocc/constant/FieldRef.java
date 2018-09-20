package javawocc.constant;

public class FieldRef extends Constant {
	private ClassConstant clazz;
	private UTF8Constant utf8;

	public FieldRef(ClassConstant clazz, UTF8Constant utf8) {
		super();
		this.clazz = clazz;
		this.utf8 = utf8;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("09"); // type
		sb.append(String.format("%04x", clazz.getIndex()));
		sb.append(String.format("%04x", utf8.getIndex()));
		return sb.toString();
	}
}
