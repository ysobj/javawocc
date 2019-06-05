package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.tokenizer.Tokenizer;

public class OneToManyParser implements Parser {
	boolean atlLeastOne = true;

	Parser[] parsers;

	public OneToManyParser(Parser... parsers) {
		this.parsers = parsers;
	}

	public OneToManyParser(boolean atLeastOne, Parser... parsers) {
		this.atlLeastOne = atLeastOne;
		this.parsers = parsers;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		ASTNodeList list = new ASTNodeList();
		boolean accepted = false;
		try {
			while (tokenizer.hasNext()) {
				for (Parser parser : parsers) {
					ASTNode node = parser.parse(tokenizer);
					if (node != null) {
						list.add(node);
					}
				}
				accepted = true;
			}
		} catch (ParseException e) {
			if (atlLeastOne && !accepted) {
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
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Parser parser : parsers) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(parser.toString());
		}
		sb.append(" )+");
		return sb.toString();
	}

}
