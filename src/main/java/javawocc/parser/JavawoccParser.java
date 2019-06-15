package javawocc.parser;

import java.util.List;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.ast.IfNode;
import javawocc.ast.OperatorPrecedenceResolver;
import javawocc.ast.WhileNode;
import javawocc.parser.ParenthesesParser.Type;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

public class JavawoccParser implements Parser {
	private Parser parser;

	public JavawoccParser() {
		// factor = NUMBER | IDENTIFIER
		// expression = factor (OPERATOR factor)
		// parentheses_expression = "(" expression ")"
		// block = "{" statements "}"
		// if_statement = "if" parentheses_expression block ( "else" block )
		// while_statement = "while" parentheses_expression block
		// statement = expression TERMINATOR
		// statements = statement | if_statement | while_statement
		// program = statements *

		Parser factor = new ChoiceParser(new NumberParser(), new IdentifierParser()) {
			public String toString() {
				return "factor";
			}
		};
		Parser expression = new SequenceParser(factor, new OneToManyParser(new OperatorParser(), factor)) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				list.addAll(((ASTNodeList) list.remove(1)).getNodeList());
				OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
				return resolver.resolve(list);
			}

		};
		// Parser parenthesesExpression = new SequenceParser(new MatchParser("("),
		// expression, new MatchParser(")"));
		Parser parenthesesExpression = new ParenthesesParser(Type.PAREN, expression);
		Parser statement = new SequenceParser(expression, new TerminatorParser()) {
			@Override
			protected ASTNode build(ASTNodeList node) {
				return node;
			}

			@Override
			public String toString() {
				return "statement";
			}
		};
		// Parser block = new SequenceParser(new MatchParser("{"), statement, new
		// MatchParser("}"));
		ChoiceParser statements = new ChoiceParser();
		Parser block = new ParenthesesParser(Type.BRACE, statement);
		Parser ifStatement = new SequenceParser(new MatchParser(TokenType.KEYWORD, "if"), parenthesesExpression, block,
				new OptionParser(new SequenceParser(new MatchParser(TokenType.KEYWORD, "else"), block))) {
			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> nodelist = node.getNodeList();
				ASTNode cond = nodelist.get(1);
				ASTNode ifs = nodelist.get(2);
				if (nodelist.size() > 3) {
					ASTNodeList elseNode = (ASTNodeList) nodelist.get(3);
					return new IfNode(cond, ifs, elseNode.getNodeList().get(1));
				}
				return new IfNode(cond, ifs);
			}
		};
		Parser whileStatement = new SequenceParser(new MatchParser(TokenType.KEYWORD, "while"), parenthesesExpression,
				block) {
			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> nodelist = node.getNodeList();
				ASTNode cond = nodelist.get(1);
				ASTNode whiles = nodelist.get(2);
				return new WhileNode(cond, whiles);
			}
		};
		statements.add(statement);
		statements.add(ifStatement);
		statements.add(whileStatement);
		parser = new OneToManyParser(statements);
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		return parser.parse(tokenizer);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

}
