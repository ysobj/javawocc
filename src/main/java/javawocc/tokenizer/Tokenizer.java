package javawocc.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;

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
		// if (buffer != null) {
		// char tmp = (char) buffer.intValue();
		// buffer = null;
		// return createToken("" + tmp);
		// }
		try {
			StringBuilder sb = new StringBuilder();
			while (true) {
				int r = is.read();
				switch (r) {
				case ';':
				case '(':
				case ')':
				case '{':
				case '}':
				case '+':
				case '-':
				case '*':
				case '/':
				case '<':
					return createToken("" + (char) r);
				case '=':
					is.mark(1);
					int r2 = is.read();
					if ((char) r2 == '=') {
						return createToken("==");
					}
					is.reset();
					return createToken("=");
				case ' ':
					break;
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					return createNumberToken(r, is);
				case -1:
					if (sb.length() == 0) {
						this.preloaded = EOS;
						return EOS;
					}
					return createToken(sb.toString());
				default:
					return createIdToken(r, is);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Token("1234", TokenType.NUMBER);
	}

	private Token createIdToken(int r, Reader is2) {
		StringBuilder sb = new StringBuilder();
		sb.append((char) r);
		try {
			is.mark(1);
			r = is.read();
			while (!in(r, ';', '(', ')', '{', '}', '+', '-', '*', '/', ' ', '=') && r != -1) {
				sb.append((char) r);
				is.mark(1);
				r = is.read();
			}
			is.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String origin = sb.toString();
		return new Token(origin, resolveType(origin));
	}

	private Token createNumberToken(int r, Reader is) {
		StringBuilder sb = new StringBuilder();
		sb.append((char) r);
		try {
			is.mark(1);
			r = is.read();
			while (r >= '0' && r <= '9') {
				sb.append((char) r);
				is.mark(1);
				r = is.read();
			}
			is.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Token(sb.toString(), TokenType.NUMBER);
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
		if (x == ';') {
			return TokenType.TERMINATOR;
		}
		if (in(string, "+", "-", "*", "/", "=", "<", "==")) {
			return TokenType.OPERATOR;
		}
		if (in(string, "if", "else", "while")) {
			return TokenType.KEYWORD;
		}
		if (x == '(') {
			return TokenType.PAREN_OPEN;
		}
		if (x == ')') {
			return TokenType.PAREN_CLOSE;
		}
		if (x == '{') {
			return TokenType.BRACE_OPEN;
		}
		if (x == '}') {
			return TokenType.BRACE_CLOSE;
		}
		return TokenType.IDENTIFIER;
	}

	private boolean in(String obj, String... candidates) {
		return Arrays.stream(candidates).anyMatch((c) -> obj.equals(c));
	}

	private boolean in(int obj, int... candidates) {
		return Arrays.stream(candidates).anyMatch((c) -> obj == c);
	}

	@Override
	public String toString() {
		if (this.preloaded != null) {
			return this.preloaded.getOriginal();
		}
		return "";
	}
}
