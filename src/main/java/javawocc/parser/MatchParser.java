package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.Identifier;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

public class MatchParser implements Parser {
	private String original;

	public MatchParser(String original) {
		this.original = original;
	}


	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		Token token = tokenizer.peek();
		if (token == null || token.getType() == TokenType.EOS || !original.equals(token.getOriginal())) {
			throw new ParseException();
		}
		token = tokenizer.next();
		return build(token);
	}

	protected ASTNode build(Token token) {
		return new Identifier(token);
	}

}
