package javawocc.parser;

import java.util.List;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.ast.OperatorPrecedenceResolver;
import javawocc.tokenizer.Tokenizer;

public class JavawoccParser implements Parser {
	private Parser parser;

	public JavawoccParser() {
		// factor = NUMBER | IDENTIFIER
		// expression = factor (OPERATOR factor)
		// parentheses_expression = "(" expression ")"
		// block = "{" statement "}"
		// if_statement = "if" parentheses_expression block
		// statement = expression (TERMINATOR expression)
		// statements = statement TERMINATOR | if_statement
		// program = statements *

		Parser factor = new ChoiceParser(new NumberParser(), new IdentifierParser());
		Parser expression = new SequenceParser(factor, new OneToManyParser(new OperatorParser(), factor)) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				list.addAll(((ASTNodeList) list.remove(1)).getNodeList());
				OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
				return resolver.resolve(list);
			}

		};
		Parser parenthesesExpression = new SequenceParser(new MatchParser("("), expression, new MatchParser(")"));
		Parser statement = new SequenceParser(expression, new OneToManyParser(new TerminatorParser(), expression)) {
			@Override
			protected ASTNode build(ASTNodeList node) {
				return node;
			}
		};
		Parser block = new SequenceParser(new MatchParser("{"), statement, new MatchParser("}"));
		Parser ifStatement = new SequenceParser(new MatchParser("if"), parenthesesExpression, block);
		Parser statements = new ChoiceParser(new SequenceParser(statement, new TerminatorParser()), ifStatement);
		parser = new OneToManyParser(statements);
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		return parser.parse(tokenizer);
	}

}
