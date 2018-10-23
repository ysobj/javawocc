package javawocc.ast;

import javawocc.tokenizer.Token;

public class Keyword extends ASTNode {

	public Keyword(Token token) {
		super(token);
	}

	@Override
	public String toString() {
		return token.getOriginal();
	}
	
	
}
