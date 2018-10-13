package javawocc.ast;

import javawocc.model.Environment;
import javawocc.tokenizer.Token;

public class ASTNode {
	protected Token token;

	public ASTNode(Token token) {
		super();
		this.token = token;
	}

	public Object evaluate(Environment env) {
		return null;
	}

	public String compile(Environment env) {
		return null;
	}
}
