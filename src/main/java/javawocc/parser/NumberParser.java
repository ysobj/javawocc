package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.NumberLiteral;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Token.TokenType;
import javawocc.tokenizer.Tokenizer;

public class NumberParser implements Parser {

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		Token token = tokenizer.peek();
		if (token == null || token.getType() == TokenType.EOS || token.getType() != TokenType.NUMBER) {
			throw new ParseException();
		}
		token = tokenizer.next();
		return new NumberLiteral(token);
	}

	@Override
	public String toString() {
		return "NUMBER";
	}

}
