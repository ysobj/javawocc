package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.Identifier;
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
		return build(token);
	}

	protected ASTNode build(Token token) {
		return new Identifier(token);
	}

	@Override
	public String toString() {
		return "IDENTIFIER";
	}

}
