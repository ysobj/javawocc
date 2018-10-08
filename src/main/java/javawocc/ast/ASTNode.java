package javawocc.ast;

import javawocc.model.Environment;

public abstract class ASTNode {
	public abstract Object evaluate(Environment env);

	public abstract String compile(Environment env);
}
