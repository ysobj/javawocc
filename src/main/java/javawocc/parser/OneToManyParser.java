package javawocc.parser;

import java.util.Arrays;

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
		try {
			while (tokenizer.hasNext()) {
				for (Parser parser : parsers) {
					ASTNode node = parser.parse(tokenizer);
					if (node != null) {
						list.add(node);
					}
				}
			}
		} catch (ParseException e) {
			if (list.getNodeList().size() == 0) {
				throw e;
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
