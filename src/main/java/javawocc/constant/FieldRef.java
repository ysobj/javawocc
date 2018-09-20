package javawocc.constant;

public class FieldRef extends Constant {
	private ClassConstant clazz;
	private NameAndType nameAndType;

	public FieldRef(ClassConstant clazz, NameAndType nameAndType) {
		super();
		this.clazz = clazz;
		this.nameAndType = nameAndType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("09"); // type
		sb.append(String.format("%04x", clazz.getIndex()));
		sb.append(String.format("%04x", nameAndType.getIndex()));
		return sb.toString();
	}
}
