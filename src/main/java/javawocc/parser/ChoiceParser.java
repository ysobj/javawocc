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
		throw new ParseException();
	}

}
