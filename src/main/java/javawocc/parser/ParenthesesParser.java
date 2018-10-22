package javawocc.parser;

import javawocc.tokenizer.Token.TokenType;

public class ParenthesesParser extends SequenceParser {
	public enum Type {
		PAREN, BRACE
	}

	public ParenthesesParser(Type type, Parser... parsers) {
		Parser[] tmp = new Parser[parsers.length + 2];
		if (type == Type.PAREN) {
			tmp[0] = new MatchParser(TokenType.PAREN_OPEN,"(");
		} else {
			tmp[0] = new MatchParser(TokenType.BRACE_OPEN,"{");
		}
		System.arraycopy(parsers, 0, tmp, 1, parsers.length);
		if (type == Type.PAREN) {
			tmp[tmp.length - 1] = new MatchParser(TokenType.PAREN_CLOSE,")");
		} else {
			tmp[tmp.length - 1] = new MatchParser(TokenType.BRACE_CLOSE,"}");
		}
		this.parsers = tmp;
	}
}
