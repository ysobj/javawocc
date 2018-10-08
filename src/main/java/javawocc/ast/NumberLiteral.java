package javawocc.ast;

import javawocc.model.Environment;

public class NumberLiteral extends ASTNode {
	private Integer value;

	public NumberLiteral(String value) {
		this.value = Integer.parseInt(value);
	}

	@Override
	public Object evaluate(Environment env) {
		return value;
	}

	@Override
	public String compile(Environment env) {
		StringBuilder sb = new StringBuilder();
		sb.append("11"); // sipush
		sb.append(String.format("%04x", value));
		return sb.toString();
	}

	@Override
	public String toString() {
		return "" + value;
	}

}
