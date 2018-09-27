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
		BinaryExpression target = new BinaryExpression(left, "+", right);
		assertEquals(357, target.evaluate());
		BinaryExpression target2 = new BinaryExpression(right, "-", left);
		assertEquals(111, target2.evaluate());
		assertEquals("11007b1100ea60", target.compile());
		assertEquals("1100ea11007b64", target2.compile());
	}
}
