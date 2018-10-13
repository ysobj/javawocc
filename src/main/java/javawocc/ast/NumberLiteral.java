package javawocc.ast;

import javawocc.model.Environment;
import javawocc.tokenizer.Token;

public class NumberLiteral extends ASTNode {
	private Integer value;

	public NumberLiteral(Token token) {
		super(token);
		this.value = Integer.parseInt(token.getOriginal());
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
