package javawocc.ast;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javawocc.tokenizer.Token;
import javawocc.tokenizer.Token.TokenType;

class OperatorPrecedenceResolverTest {
	protected NumberLiteral number(String str) {
		return new NumberLiteral(new Token(str, TokenType.NUMBER));
	}

	protected OperatorNode operator(String str) {
		return new OperatorNode(new Token(str, TokenType.OPERATOR));
	}

	@Test
	void test1() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(number("2"));
		list.add(operator("+"));
		list.add(number("3"));
		list.add(operator("+"));
		list.add(number("4"));
		list.add(operator("+"));
		list.add(number("5"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("(((2 + 3) + 4) + 5)", node.toString());
	}

	@Test
	void test2() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(number("2"));
		list.add(operator("+"));
		list.add(number("3"));
		list.add(operator("*"));
		list.add(number("4"));
		list.add(operator("+"));
		list.add(number("5"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("((2 + (3 * 4)) + 5)", node.toString());
	}

	@Test
	void test3() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(number("2"));
		list.add(operator("*"));
		list.add(number("3"));
		list.add(operator("+"));
		list.add(number("4"));
		list.add(operator("*"));
		list.add(number("5"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("((2 * 3) + (4 * 5))", node.toString());
	}

	@Test
	void testRightToLeft() {
		OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
		List<ASTNode> list = new ArrayList<>();
		list.add(number("2"));
		list.add(operator("="));
		list.add(number("3"));
		list.add(operator("="));
		list.add(number("4"));
		ASTNode node = resolver.resolve(list);
		assertNotNull(node);
		assertEquals("(2 = (3 = 4))", node.toString());
	}
}
