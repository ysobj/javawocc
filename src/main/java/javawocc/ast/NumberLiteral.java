package javawocc.ast;

public class NumberLiteral extends ASTNode {
	private Integer value;

	public NumberLiteral(String value) {
		this.value = Integer.parseInt(value);
	}

	@Override
	public Object evaluate() {
		return value;
	}

	@Override
	public String compile() {
		return String.format("%04x", value);
	}

}
