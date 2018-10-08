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
	public String compile(Environment env) {
		return "1b"; // iload_1 TODO adhoc implementation
	}

	@Override
	public String toString() {
		return original;
	}

}
