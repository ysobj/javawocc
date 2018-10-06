package javawocc.tokenizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.tokenizer.Token;
import javawocc.tokenizer.Token.TokenType;
import javawocc.tokenizer.Tokenizer;

class TokenizerTest {
	@Test
	void testSimpleCase() {
		Tokenizer tokenizer = new Tokenizer("a = 1234 +   5678; b = 123");
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

}
