package javawocc.parser;

import javawocc.tokenizer.Token;

public class ParseException extends Exception {

	private static final long serialVersionUID = 1L;

	private Token token;
	
	private String expected;

	public ParseException(Token token) {
		super();
		this.token = token;
	}

	public ParseException(String expected, Token token) {
		super();
		this.expected = expected;
		this.token = token;
	}

	@Override
	public String getMessage() {
		if(expected != null) {
			return String.format("expected: %s, actual: %s", expected, token.toString());
		}
		return token.toString();
	}

}
