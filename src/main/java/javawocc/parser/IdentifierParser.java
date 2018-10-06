package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.model.Identifier;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

public class IdentifierParser implements Parser {

	public IdentifierParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		Token token = tokenizer.peek();
		if (token == null || token.getType() == TokenType.EOS || token.getType() != TokenType.IDENTIFIER) {
			throw new ParseException();
		}
		token = tokenizer.next();
		return new Identifier(token.getOriginal());
	}

}