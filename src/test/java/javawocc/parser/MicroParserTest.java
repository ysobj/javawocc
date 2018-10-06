package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.ast.BinaryExpression;
import javawocc.ast.OperatorNode;
import javawocc.model.Environment;
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
		Environment env = new Environment();
		assertEquals(12 + 34, parser.parse(new Tokenizer("12 + 34")).evaluate(env));
	}

	@Test
	void test2() throws Exception {
		Parser parser = new SequenceParser(new NumberParser(),
				new OneToManyParser(new OperatorParser(), new NumberParser())) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				list.addAll(((ASTNodeList) list.remove(1)).getNodeList());
				while (list.size() > 1) {
					ASTNode left = list.remove(0);
					OperatorNode op = (OperatorNode) list.remove(0);
					ASTNode right = list.remove(0);
					BinaryExpression expression = new BinaryExpression(left, op, right);
					list.add(0, expression);
				}
				return list.get(0);
			}

		};
		Environment env = new Environment();
		assertEquals(12 + 34, parser.parse(new Tokenizer("12 + 34")).evaluate(env));
		assertEquals(12 + 34 + 34, parser.parse(new Tokenizer("12 + 34 + 34")).evaluate(env));
		// should be 114, but microparser has not implement order of operators yet.
		assertEquals((12 + 34 + 34) * 2, parser.parse(new Tokenizer("12 + 34 + 34 * 2")).evaluate(env));
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
