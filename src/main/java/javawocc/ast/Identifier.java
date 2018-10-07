package javawocc.ast;

import javawocc.ast.ASTNode;
import javawocc.model.Environment;

public class Identifier extends ASTNode {
	private String original;

	public Identifier(String original) {
		this.original = original;
	}

	@Override
	public Object evaluate(Environment env) {
		return env.get(original);
	}

	@Override
	public String compile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return original;
	}

}
