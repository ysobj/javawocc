package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.OperatorNode;
import javawocc.model.Environment;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token;
import javawocc.tokenizer.Token.TokenType;

class ParserTest {
	@Test
	void testNumberParser() throws Exception {
		Environment env = new Environment();
		NumberParser parser = new NumberParser();
		ASTNode node = parser.parse(new Tokenizer("123"));
		assertNotNull(node);
		assertEquals(123, node.evaluate(env));
		assertThrows(ParseException.class, () -> parser.parse(new Tokenizer("a")));
	}

	@Test
	void testOperatorParser() throws Exception {
		OperatorParser parser = new OperatorParser();
		ASTNode node = parser.parse(new Tokenizer("+"));
		assertNotNull(node);
		assertEquals(OperatorNode.class, node.getClass());
		assertThrows(ParseException.class, () -> parser.parse(new Tokenizer("123")));
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
	void testMatchParser() throws Exception {
		Parser parser = new MatchParser(TokenType.KEYWORD, "if");
		ASTNode node = parser.parse(new Tokenizer("if"));
		assertNotNull(node);
		assertThrows(ParseException.class, () -> {
			parser.parse(new Tokenizer("a"));
		});
	}

	@Test
	void testOneToManyParser() throws Exception {
		Parser parser = new OneToManyParser(new IdentifierParser());
		ASTNode node = parser.parse(new Tokenizer("a a a a"));
		assertNotNull(node);
		node = parser.parse(new Tokenizer("a a a 1"));
		assertNotNull(node);
	}

	@Test
	void testOneToManyParser2() throws Exception {
		Parser parser = new OneToManyParser(
				new SequenceParser(new IdentifierParser(), new IdentifierParser(), new IdentifierParser()));
		ASTNode node = parser.parse(new Tokenizer("a b c"));
		assertNotNull(node);
		node = parser.parse(new Tokenizer("a b c a b c"));
		assertNotNull(node);
		assertThrows(ParseException.class, () -> {
			parser.parse(new Tokenizer("a b"));
		});
	}

	@Test
	void testOneToManyParser3() throws Exception {
		Parser parser = new SequenceParser(new IdentifierParser(),
				new OneToManyParser(false, new NumberParser(), new IdentifierParser()));
		ASTNode node = parser.parse(new Tokenizer("a 1 b"));
		assertEquals("a1b", node.toString());
		node = parser.parse(new Tokenizer("a 1 b 2 c"));
		assertEquals("a1b2c", node.toString());
		Tokenizer tokenizer = new Tokenizer("a b");
		node = parser.parse(tokenizer);
		assertEquals("a", node.toString());
		assertTrue(tokenizer.hasNext());
		Token token = tokenizer.next();
		assertEquals("b", token.getOriginal());
	}

	@Test
	void testOptionParser() throws Exception {
		Parser parser = new SequenceParser(new IdentifierParser(), new OptionParser(new NumberParser()),
				new IdentifierParser());
		ASTNode node = parser.parse(new Tokenizer("a 1 c"));
		assertNotNull(node);
		assertEquals("a1c", node.toString());
		node = parser.parse(new Tokenizer("a b"));
		assertNotNull(node);
		assertEquals("ab", node.toString());
		assertThrows(ParseException.class, () -> parser.parse(new Tokenizer("a 3 4")));
	}
}
