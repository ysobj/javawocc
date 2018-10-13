package javawocc.parser;

import javawocc.tokenizer.Token;

public class ParseException extends Exception {

	private static final long serialVersionUID = 1L;

	private Token token;

	public ParseException(Token token) {
		super();
		this.token = token;
	}

	@Override
	public String getMessage() {
		return token.toString();
	}

}
