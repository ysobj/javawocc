package javawocc.parser;

import java.util.Arrays;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.tokenizer.Tokenizer;

public class SequenceParser implements Parser {
	Parser[] parsers;

	public SequenceParser(Parser... parsers) {
		this.parsers = parsers;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		ASTNodeList list = new ASTNodeList();
		for (Parser parser : parsers) {
			ASTNode node = parser.parse(tokenizer);
			if (node != null) {
				list.add(node);
			}
		}
		return build(list);
	}

	protected ASTNode build(ASTNodeList node) {
		return node;
	}

	@Override
	public String toString() {
		return Arrays.toString(parsers);
	}
}
