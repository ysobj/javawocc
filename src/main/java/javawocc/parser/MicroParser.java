package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.BinaryExpression;
import javawocc.ast.NumberLiteral;
import javawocc.ast.OperatorNode;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Tokenizer;

public class MicroParser implements Parser {

	@Override
	public ASTNode parse(Tokenizer tokenizer) {
		ASTNode left = null;
		OperatorNode mid = null;
		while (tokenizer.hasNext()) {
			Token token = tokenizer.next();
			if (left == null) {
				if (token.getType() != Token.TokenType.NUMBER) {
					throw new IllegalArgumentException();
				}
				left = new NumberLiteral(token);
				continue;
			}
			if (mid == null) {
				if (token.getType() != Token.TokenType.OPERATOR) {
					throw new IllegalArgumentException();
				}
				mid = new OperatorNode(token);
				continue;
			}
			if (token.getType() != Token.TokenType.NUMBER) {
				throw new IllegalArgumentException();
			}
			left = new BinaryExpression(left, mid, new NumberLiteral(token));
			mid = null;
		}
		if (mid != null) {
			throw new IllegalArgumentException();
		}
		return left;
	}

}
