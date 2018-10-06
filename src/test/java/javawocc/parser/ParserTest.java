package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.OperatorNode;
import javawocc.tokenizer.Tokenizer;

class ParserTest {

	@Test
	void testNumberParser() throws Exception {
		NumberParser parser = new NumberParser();
		ASTNode node = parser.parse(new Tokenizer("123"));
		assertNotNull(node);
		assertEquals(123, node.evaluate());
		assertThrows(ParseException.class, () -> parser.parse(new Tokenizer("a")));
	}

	@Test
	void testOperatorParser() throws Exception {
		OperatorParser parser = new OperatorParser();
		ASTNode node = parser.parse(new Tokenizer("+"));
		assertNotNull(node);
		assertEquals(OperatorNode.class, node.getClass());
		node = parser.parse(new Tokenizer("123"));
		assertNull(node);
	}

	@Test
	void testSequenceParser() throws Exception {
		NumberParser p1 = new NumberParser();
		OperatorParser p2 = new OperatorParser();
		SequenceParser parser = new SequenceParser(p1, p2);
		assertNotNull(parser.parse(new Tokenizer("123 +")));
	}

	@Test
	void testChoiceParser() throws Exception {
		NumberParser p1 = new NumberParser();
		OperatorParser p2 = new OperatorParser();
		ChoiceParser parser = new ChoiceParser(p1, p2);
		assertNotNull(parser.parse(new Tokenizer("123")));
		assertNotNull(parser.parse(new Tokenizer("+")));
		assertThrows(ParseException.class, () -> parser.parse(new Tokenizer("a")));
	}

	@Test
	void testJavawoccParser() throws Exception {
		JavawoccParser parser = new JavawoccParser();
		ASTNode node = parser.parse(new Tokenizer("a = 1"));
		assertNotNull(node);
		assertEquals("(a = 1)", node.toString());
	}
}
