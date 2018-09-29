package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.tokenizer.Tokenizer;

public class SequenceParser implements Parser {
	Parser[] parsers;

	public SequenceParser(Parser... parsers) {
		this.parsers = parsers;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) {
		ASTNode node = null;
		for (Parser parser : parsers) {
			node = parser.parse(tokenizer);
		}
		return node;
	}

}
