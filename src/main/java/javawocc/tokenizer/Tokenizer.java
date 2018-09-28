package javawocc.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javawocc.tokenizer.Token.TokenType;

public class Tokenizer {
	private Reader is;
	private Token preloaded;
	private static final Token EOS = new Token("", TokenType.EOS);

	public Tokenizer(String string) {
		is = new StringReader(string);
	}

	public boolean hasNext() {
		if (preloaded != null) {
			if (preloaded.getType() == TokenType.EOS) {
				return false;
			}
			return true;
		}
		preloaded = next();
		if (preloaded != null) {
			if (preloaded.getType() == TokenType.EOS) {
				return false;
			}
			return true;
		}
		return false;
	}

	public Token next() {
		if (preloaded != null) {
			Token t = this.preloaded;
			this.preloaded = null;
			return t;
		}
		try {
			StringBuilder sb = new StringBuilder();
			while (true) {
				int r = is.read();
				switch (r) {
				case ' ':
					return createToken(sb.toString());
				case -1:
					if (sb.length() == 0) {
						this.preloaded = EOS;
						return EOS;
					}
					return createToken(sb.toString());
				default:
					sb.append((char) r);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Token("1234", TokenType.NUMBER);
	}

	public Token peek() {
		if (preloaded != null) {
			return preloaded;
		}
		hasNext();
		return preloaded;
	}

	private Token createToken(String string) {
		return new Token(string, resolveType(string));
	}

	private TokenType resolveType(String string) {
		char x = string.charAt(0);
		if (x >= '0' && x <= '9') {
			return TokenType.NUMBER;
		}
		return TokenType.OPERATOR;
	}

}
