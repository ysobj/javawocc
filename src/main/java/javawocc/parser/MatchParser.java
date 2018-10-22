package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.Keyword;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

public class MatchParser implements Parser {
	private String original;
	private TokenType type;

	public MatchParser(TokenType type, String original) {
		this.original = original;
		this.type = type;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		Token token = tokenizer.peek();
		if (token == null || token.getType() != type || !original.equals(token.getOriginal())) {
			throw new ParseException(this.original, token);
		}
		token = tokenizer.next();
		return build(token);
	}

	protected ASTNode build(Token token) {
		return new Keyword(token);
	}

	@Override
	public String toString() {
		return original;
	}
}
