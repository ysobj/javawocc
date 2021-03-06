package javawocc.tokenizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.tokenizer.Token;
import javawocc.tokenizer.Token.TokenType;
import javawocc.tokenizer.Tokenizer;

class TokenizerTest {
	@Test
	void testSimpleCase() {
		Tokenizer tokenizer = new Tokenizer("a = 1234 +   5678; bc = 123");
		//
		assertTrue(tokenizer.hasNext());
		Token token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("1234", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("+", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("5678", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(";", token.getOriginal());
		assertEquals(TokenType.TERMINATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("bc", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("123", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertFalse(tokenizer.hasNext());
	}

	@Test
	void testSimplePeek() {
		Tokenizer tokenizer = new Tokenizer("1234 + 5678");
		Token token = tokenizer.peek();
		assertNotNull(token);
		assertEquals("1234", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		token = tokenizer.peek();
		assertNotNull(token);
		assertEquals("1234", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
	}

	@Test
	void testKeyword() {
		Tokenizer tokenizer = new Tokenizer("if(a == 1){ b = 2 }else{ c=5 }");
		//
		assertTrue(tokenizer.hasNext());
		Token token = tokenizer.next();
		assertNotNull(token);
		assertEquals("if", token.getOriginal());
		assertEquals(TokenType.KEYWORD, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("(", token.getOriginal());
		assertEquals(TokenType.PAREN_OPEN, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("==", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("1", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(")", token.getOriginal());
		assertEquals(TokenType.PAREN_CLOSE, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("{", token.getOriginal());
		assertEquals(TokenType.BRACE_OPEN, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("b", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("2", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("}", token.getOriginal());
		assertEquals(TokenType.BRACE_CLOSE, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("else", token.getOriginal());
		assertEquals(TokenType.KEYWORD, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("{", token.getOriginal());
		assertEquals(TokenType.BRACE_OPEN, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("c", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("5", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("}", token.getOriginal());
		assertEquals(TokenType.BRACE_CLOSE, token.getType());
	}

	@Test
	void testReturn() {
		Tokenizer tokenizer = new Tokenizer("a=0;return a;");
		assertTrue(tokenizer.hasNext());
		Token token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("0", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(";", token.getOriginal());
		assertEquals(TokenType.TERMINATOR, token.getType());

		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("return", token.getOriginal());
		assertEquals(TokenType.KEYWORD, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(";", token.getOriginal());
		assertEquals(TokenType.TERMINATOR, token.getType());

	}

	@Test
	void testWhile() {
		Tokenizer tokenizer = new Tokenizer("a=0;while(a < 5){ a = a + 1;}");

		//
		assertTrue(tokenizer.hasNext());
		Token token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("0", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(";", token.getOriginal());
		assertEquals(TokenType.TERMINATOR, token.getType());

		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("while", token.getOriginal());
		assertEquals(TokenType.KEYWORD, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("(", token.getOriginal());
		assertEquals(TokenType.PAREN_OPEN, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("<", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("5", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(")", token.getOriginal());
		assertEquals(TokenType.PAREN_CLOSE, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("{", token.getOriginal());
		assertEquals(TokenType.BRACE_OPEN, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("+", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("1", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());

		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals(";", token.getOriginal());
		assertEquals(TokenType.TERMINATOR, token.getType());

		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("}", token.getOriginal());
		assertEquals(TokenType.BRACE_CLOSE, token.getType());
	}

	@Test
	void testNotSeparatedWithSpace() {
		Tokenizer tokenizer = new Tokenizer("a=1");
		assertTrue(tokenizer.hasNext());
		Token token = tokenizer.next();
		assertNotNull(token);
		assertEquals("a", token.getOriginal());
		assertEquals(TokenType.IDENTIFIER, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("=", token.getOriginal());
		assertEquals(TokenType.OPERATOR, token.getType());
		//
		assertTrue(tokenizer.hasNext());
		token = tokenizer.next();
		assertNotNull(token);
		assertEquals("1", token.getOriginal());
		assertEquals(TokenType.NUMBER, token.getType());
	}

}
