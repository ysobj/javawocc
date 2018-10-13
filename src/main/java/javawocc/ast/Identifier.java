package javawocc.ast;

import javawocc.ast.ASTNode;
import javawocc.model.Environment;
import javawocc.tokenizer.Token;

public class Identifier extends ASTNode {
	private static final int iload0 = 26;
	private static final int istore0 = 59;

	public Identifier(Token token) {
		super(token);
	}

	@Override
	public Object evaluate(Environment env) {
		return env.get(token.getOriginal());
	}

	@Override
	public String compile(Environment env) {
		int ind = iload0 + env.getIndex(token.getOriginal());
		return String.format("%02x", ind);
	}

	public String compileLeftHandSide(Environment env) {
		int ind = istore0 + env.getIndex(token.getOriginal());
		return String.format("%02x", ind);
	}

	@Override
	public String toString() {
		return token.getOriginal();
	}

}
