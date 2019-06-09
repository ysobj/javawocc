package javawocc.ast;

import javawocc.tokenizer.Token;

public class NullNode extends ASTNode {

	public NullNode() {
		super(null);
	}

	public NullNode(Token token) {
		super(token);
	}
}
