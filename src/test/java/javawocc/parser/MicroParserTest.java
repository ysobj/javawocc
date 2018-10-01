package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.ast.BinaryExpression;
import javawocc.ast.OperatorNode;
import javawocc.tokenizer.Tokenizer;

class MicroParserTest {

	@Test
	void test() {
		Parser parser = new SequenceParser(new NumberParser(), new OperatorParser(), new NumberParser()) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				BinaryExpression expression = new BinaryExpression(list.get(0), (OperatorNode) list.get(1),
						list.get(2));
				return expression;
			}

		};

		assertEquals(12 + 34, parser.parse(new Tokenizer("12 + 34")).evaluate());
	}

	@Test
	void test2() {
		Parser parser = new SequenceParser(new NumberParser(), new OperatorParser(), new NumberParser()) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				BinaryExpression expression = new BinaryExpression(list.get(0), (OperatorNode) list.get(1),
						list.get(2));
				return expression;
			}

		};

		assertEquals(12 + 34, parser.parse(new Tokenizer("12 + 34")).evaluate());
		assertEquals(12 + 34 + 34, parser.parse(new Tokenizer("12 + 34 + 34")).evaluate());
		// should be 114, but microparser has not implement order of operators yet.
		assertEquals((12 + 34 + 34) * 2, parser.parse(new Tokenizer("12 + 34 + 34 * 2")).evaluate());
	}
}
