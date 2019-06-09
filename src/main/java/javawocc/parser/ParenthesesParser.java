package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.ast.NullNode;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Token.TokenType;

public class ParenthesesParser extends SequenceParser {
	public enum Type {
		PAREN, BRACE
	}

	public ParenthesesParser(Type type, Parser... parsers) {
		Parser[] tmp = new Parser[parsers.length + 2];
		if (type == Type.PAREN) {
			tmp[0] = new MatchParser(TokenType.PAREN_OPEN, "(") {

				@Override
				protected ASTNode build(Token token) {
					return new NullNode();
				}

			};
		} else {
			tmp[0] = new MatchParser(TokenType.BRACE_OPEN, "{") {

				@Override
				protected ASTNode build(Token token) {
					return new NullNode();
				}

			};
		}
		System.arraycopy(parsers, 0, tmp, 1, parsers.length);
		if (type == Type.PAREN) {
			tmp[tmp.length - 1] = new MatchParser(TokenType.PAREN_CLOSE, ")") {

				@Override
				protected ASTNode build(Token token) {
					return new NullNode();
				}

			};
		} else {
			tmp[tmp.length - 1] = new MatchParser(TokenType.BRACE_CLOSE, "}") {

				@Override
				protected ASTNode build(Token token) {
					return new NullNode();
				}

			};
		}
		this.parsers = tmp;
	}

	@Override
	protected ASTNode build(ASTNodeList node) {
		// TODO Auto-generated method stub
		return super.build(node);
	}

}
