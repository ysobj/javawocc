package javawocc.tokenizer;

public class Token {
	public static enum TokenType {
		IDENTIFIER, NUMBER, OPERATOR, EOS, TERMINATOR, KEYWORD, PAREN_OPEN, PAREN_CLOSE, BRACE_OPEN, BRACE_CLOSE
	}

	private final String original;
	private final TokenType type;

	public Token(String original, TokenType type) {
		this.original = original;
		this.type = type;
	}

	public String getOriginal() {
		return original;
	}

	public TokenType getType() {
		return type;
	}

}
