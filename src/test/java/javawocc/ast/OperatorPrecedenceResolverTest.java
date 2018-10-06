package javawocc.ast;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class OperatorPrecedenceResolverTest {

	@Test
	void test1() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(new NumberLiteral("2"));
		list.add(new OperatorNode("+"));
		list.add(new NumberLiteral("3"));
		list.add(new OperatorNode("+"));
		list.add(new NumberLiteral("4"));
		list.add(new OperatorNode("+"));
		list.add(new NumberLiteral("5"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("(((2 + 3) + 4) + 5)", node.toString());
	}

	@Test
	void test2() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(new NumberLiteral("2"));
		list.add(new OperatorNode("+"));
		list.add(new NumberLiteral("3"));
		list.add(new OperatorNode("*"));
		list.add(new NumberLiteral("4"));
		list.add(new OperatorNode("+"));
		list.add(new NumberLiteral("5"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("((2 + (3 * 4)) + 5)", node.toString());
	}

	@Test
	void test3() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(new NumberLiteral("2"));
		list.add(new OperatorNode("*"));
		list.add(new NumberLiteral("3"));
		list.add(new OperatorNode("+"));
		list.add(new NumberLiteral("4"));
		list.add(new OperatorNode("*"));
		list.add(new NumberLiteral("5"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("((2 * 3) + (4 * 5))", node.toString());
	}

	@Test
	void testRightToLeft() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(new NumberLiteral("2"));
		list.add(new OperatorNode("="));
		list.add(new NumberLiteral("3"));
		list.add(new OperatorNode("="));
		list.add(new NumberLiteral("4"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("(2 = (3 = 4))", node.toString());
	}
}
