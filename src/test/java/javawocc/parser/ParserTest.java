package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.OperatorNode;
import javawocc.tokenizer.Tokenizer;

class ParserTest {

	@Test
	void testNumberParser() {
		NumberParser parser = new NumberParser();
		ASTNode node = parser.parse(new Tokenizer("123"));
		assertNotNull(node);
		assertEquals(123, node.evaluate());
		node = parser.parse(new Tokenizer("a"));
		assertNull(node);
	}

	@Test
	void testOperatorParser() {
		OperatorParser parser = new OperatorParser();
		ASTNode node = parser.parse(new Tokenizer("+"));
		assertNotNull(node);
		assertEquals(OperatorNode.class, node.getClass());
		node = parser.parse(new Tokenizer("123"));
		assertNull(node);
	}

	@Test
	void testSequenceParser() {
		NumberParser p1 = new NumberParser();
		OperatorParser p2 = new OperatorParser();
		SequenceParser parser = new SequenceParser(p1, p2);
		assertNotNull(parser.parse(new Tokenizer("123 +")));
	}
}
