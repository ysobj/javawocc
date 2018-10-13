package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.OperatorNode;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

public class OperatorParser implements Parser {

	@Override
	public ASTNode parse(Tokenizer tokenizer) {
		Token token = tokenizer.peek();
		if (token == null || token.getType() == TokenType.EOS || token.getType() != TokenType.OPERATOR) {
			return null;
		}
		token = tokenizer.next();
		return new OperatorNode(token);
	}

	@Override
	public String toString() {
		return "OPERATOR";
	}

}
