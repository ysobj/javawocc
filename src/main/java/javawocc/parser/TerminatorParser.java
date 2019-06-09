package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.NullNode;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

public class TerminatorParser implements Parser {

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		Token token = tokenizer.peek();
		if (token == null || (token.getType() != TokenType.EOS && token.getType() != TokenType.TERMINATOR)) {
			throw new ParseException(token);
		}
		return new NullNode(tokenizer.next());
	}

	@Override
	public String toString() {
		return "TERMINATOR";
	}

}
