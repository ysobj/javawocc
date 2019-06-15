package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.NullNode;
import javawocc.tokenizer.Tokenizer;

public class OptionParser implements Parser {
	private Parser parser;

	public OptionParser(Parser parser) {
		this.parser = parser;
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		if (tokenizer.hasNext()) {
			try {
				return parser.parse(tokenizer);
			} catch (ParseException e) {

			}
		}
		return new NullNode();
	}

	@Override
	public String toString() {
		return "(" + this.parser.toString() + ")";
	}

}
