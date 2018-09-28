package javawocc.ast;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ASTNodeTest {

	@Test
	void testNumberLiteral() {
		NumberLiteral num = new NumberLiteral("123");
		assertEquals(123, num.evaluate());
		assertEquals("007b", num.compile());
	}

	@Test
	void testBinaryExpression() {
		NumberLiteral left = new NumberLiteral("123");
		NumberLiteral right = new NumberLiteral("234");
		BinaryExpression target = new BinaryExpression(left, new OperatorNode("+"), right);
		assertEquals(357, target.evaluate());
		BinaryExpression target2 = new BinaryExpression(right, new OperatorNode("-"), left);
		assertEquals(111, target2.evaluate());
		assertEquals("11007b1100ea60", target.compile());
		assertEquals("1100ea11007b64", target2.compile());
	}

	@Test
	void testBinaryExpression2() {
		NumberLiteral left = new NumberLiteral("123");
		NumberLiteral right = new NumberLiteral("234");
		BinaryExpression target = new BinaryExpression(left, new OperatorNode("+"), right);
		NumberLiteral rr = new NumberLiteral("321");
		BinaryExpression target2 = new BinaryExpression(target, new OperatorNode("-"), rr);
		assertEquals(36, target2.evaluate());
	}

	@Test
	void testBinaryExpressionOperators() {
		NumberLiteral left = new NumberLiteral("9");
		NumberLiteral right = new NumberLiteral("3");
		BinaryExpression plus = new BinaryExpression(left, new OperatorNode("+"), right);
		assertEquals(12, plus.evaluate());
		BinaryExpression minus = new BinaryExpression(left, new OperatorNode("-"), right);
		assertEquals(6, minus.evaluate());
		BinaryExpression mul = new BinaryExpression(left, new OperatorNode("*"), right);
		assertEquals(27, mul.evaluate());
		BinaryExpression div = new BinaryExpression(left, new OperatorNode("/"), right);
		assertEquals(3, div.evaluate());
	}
}
