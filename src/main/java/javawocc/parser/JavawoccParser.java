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
		// statement = factor (OPERATOR factor)
		// statements = statement (TERMINATOR statement)

		Parser factor = new ChoiceParser(new NumberParser(), new IdentifierParser());
		Parser statement = new SequenceParser(factor, new OneToManyParser(new OperatorParser(), factor)) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				list.addAll(((ASTNodeList) list.remove(1)).getNodeList());
				OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
				return resolver.resolve(list);
			}

		};
		parser = new SequenceParser(statement, new OneToManyParser(new TerminatorParser(), statement)) {
			@Override
			protected ASTNode build(ASTNodeList node) {
				return node;
			}
		};
	}

	@Override
	public ASTNode parse(Tokenizer tokenizer) throws ParseException {
		return parser.parse(tokenizer);
	}

}
