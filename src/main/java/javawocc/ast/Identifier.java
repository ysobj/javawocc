package javawocc.ast;

import javawocc.ast.ASTNode;
import javawocc.model.Environment;

public class Identifier extends ASTNode {
	private static final int iload0 = 26;
	private static final int istore0 = 59;
	private String original;

	public Identifier(String original) {
		this.original = original;
	}

	@Override
	public Object evaluate(Environment env) {
		return env.get(original);
	}

	@Override
	public String compile(Environment env) {
		int ind = iload0 + env.getIndex(original);
		return String.format("%02x", ind);
	}

	public String compileLeftHandSide(Environment env) {
		int ind = istore0 + env.getIndex(original);
		return String.format("%02x", ind);
	}

	@Override
	public String toString() {
		return original;
	}

}
