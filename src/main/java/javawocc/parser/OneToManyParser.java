package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.tokenizer.Tokenizer;

public class OneToManyParser implements Parser {
	Parser[] parsers;

	public OneToManyParser(Parser... parsers) {
		this.parsers = parsers;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		ASTNodeList list = new ASTNodeList();
		while (tokenizer.hasNext()) {
			for (Parser parser : parsers) {
				ASTNode node = parser.parse(tokenizer);
				if (node != null) {
					list.add(node);
				}
			}
		}
		return build(list);
	}

	protected ASTNode build(ASTNodeList node) {
		return node;
	}
}
