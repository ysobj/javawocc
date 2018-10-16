package javawocc.parser;

public class ParenthesesParser extends SequenceParser {
	public enum Type {
		PAREN, BRACE
	}

	public ParenthesesParser(Type type, Parser... parsers) {
		Parser[] tmp = new Parser[parsers.length + 2];
		if (type == Type.PAREN) {
			tmp[0] = new MatchParser("(");
		} else {
			tmp[0] = new MatchParser("{");
		}
		System.arraycopy(parsers, 0, tmp, 1, parsers.length);
		if (type == Type.PAREN) {
			tmp[tmp.length - 1] = new MatchParser(")");
		} else {
			tmp[tmp.length - 1] = new MatchParser("}");
		}
		this.parsers = tmp;
	}
}
