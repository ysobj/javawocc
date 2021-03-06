package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.tokenizer.Tokenizer;

public class ChoiceParser implements Parser {
	private Parser[] parsers;

	public ChoiceParser(Parser... parsers) {
		this.parsers = parsers;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		for (Parser parser : parsers) {
			try {
				ASTNode node = parser.parse(tokenizer);
				if (node != null) {
					return node;
				}
			} catch (ParseException e) {
			}
		}
		throw new ParseException(toString(), tokenizer.peek());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Parser parser : parsers) {
			if (sb.length() > 0) {
				sb.append("|");
			}
			sb.append(parser.toString());
		}
		return sb.toString();
	}

	public void add(Parser parser) {
		Parser[] tmp = new Parser[parsers.length + 1];
		System.arraycopy(parsers, 0, tmp, 0, parsers.length);
		tmp[parsers.length] = parser;
		this.parsers = tmp;
	}
}
