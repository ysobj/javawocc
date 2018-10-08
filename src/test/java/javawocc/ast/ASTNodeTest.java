package javawocc.ast;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.model.Environment;

class ASTNodeTest {

	@Test
	void testNumberLiteral() {
		NumberLiteral num = new NumberLiteral("123");
		Environment env = new Environment();
		assertEquals(123, num.evaluate(env));
		assertEquals("11007b", num.compile(env));
		assertEquals("123", num.toString());
	}
	
	@Test
	void testOperatorNode() {
		OperatorNode op = new OperatorNode("+");
		assertEquals("+", op.toString());
	}

	@Test
	void testBinaryExpression() {
		Environment env = new Environment();
		NumberLiteral left = new NumberLiteral("123");
		NumberLiteral right = new NumberLiteral("234");
		BinaryExpression target = new BinaryExpression(left, new OperatorNode("+"), right);
		assertEquals(357, target.evaluate(env));
		assertEquals("(123 + 234)", target.toString());
		BinaryExpression target2 = new BinaryExpression(right, new OperatorNode("-"), left);
		assertEquals(111, target2.evaluate(env));
		assertEquals("11007b1100ea60", target.compile(env));
		assertEquals("1100ea11007b64", target2.compile(env));
	}

	@Test
	void testBinaryExpression2() {
		Environment env = new Environment();
		NumberLiteral left = new NumberLiteral("123");
		NumberLiteral right = new NumberLiteral("234");
		BinaryExpression target = new BinaryExpression(left, new OperatorNode("+"), right);
		NumberLiteral rr = new NumberLiteral("321");
		BinaryExpression target2 = new BinaryExpression(target, new OperatorNode("-"), rr);
		assertEquals(36, target2.evaluate(env));
		assertEquals("((123 + 234) - 321)", target2.toString());
	}

	@Test
	void testBinaryExpressionOperators() {
		Environment env = new Environment();
		NumberLiteral left = new NumberLiteral("9");
		NumberLiteral right = new NumberLiteral("3");
		BinaryExpression plus = new BinaryExpression(left, new OperatorNode("+"), right);
		assertEquals(12, plus.evaluate(env));
		BinaryExpression minus = new BinaryExpression(left, new OperatorNode("-"), right);
		assertEquals(6, minus.evaluate(env));
		BinaryExpression mul = new BinaryExpression(left, new OperatorNode("*"), right);
		assertEquals(27, mul.evaluate(env));
		BinaryExpression div = new BinaryExpression(left, new OperatorNode("/"), right);
		assertEquals(3, div.evaluate(env));
	}
}
