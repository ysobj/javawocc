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
	void test() throws Exception {
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

	void test2() throws Exception {
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

	@Test
	void testOneToManyParser() throws Exception {
		Parser parser = new OneToManyParser(new NumberParser());
		ASTNodeList node = (ASTNodeList) parser.parse(new Tokenizer("1"));
		assertEquals(1, node.getNodeList().size());
		node = (ASTNodeList) parser.parse(new Tokenizer("1 2 3"));
		assertEquals(3, node.getNodeList().size());
		node = (ASTNodeList) parser.parse(new Tokenizer("1 2 3 A"));
		assertEquals(3, node.getNodeList().size());
	}

	@Test
	void testOneToManyParserThrowsParseException() throws Exception {
		Parser parser = new OneToManyParser(new NumberParser());
		assertThrows(ParseException.class, () -> parser.parse(new Tokenizer("A")));
	}
}
