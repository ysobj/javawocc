package javawocc.model;

import javawocc.ast.ASTNode;

public class Identifier extends ASTNode {
	private String original;

	public Identifier(String original) {
		this.original = original;
	}

	@Override
	public Object evaluate(Environment env) {
		// TODO Auto-generated method stub
		return null;
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
