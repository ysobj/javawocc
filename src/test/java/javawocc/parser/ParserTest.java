package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.OperatorNode;
import javawocc.model.Environment;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

class ParserTest {
	@Test
	void testNumberParser() throws Exception {
		Environment env = new Environment();
		NumberParser parser = new NumberParser();
		ASTNode node = parser.parse(new Tokenizer("123"));
		assertNotNull(node);
		assertEquals(123, node.evaluate(env));
		assertThrows(ParseException.class,
				() -> parser.parse(new Tokenizer("a")));
	}

	@Test
	void testOperatorParser() throws Exception {
		OperatorParser parser = new OperatorParser();
		ASTNode node = parser.parse(new Tokenizer("+"));
		assertNotNull(node);
		assertEquals(OperatorNode.class, node.getClass());
		assertThrows(ParseException.class,
				() -> parser.parse(new Tokenizer("123")));
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
		assertThrows(ParseException.class,
				() -> parser.parse(new Tokenizer("a")));
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
				new SequenceParser(new IdentifierParser(),
						new IdentifierParser(), new IdentifierParser()));
		ASTNode node = parser.parse(new Tokenizer("a b c"));
		assertNotNull(node);
		node = parser.parse(new Tokenizer("a b c a b c"));
		assertNotNull(node);
		assertThrows(ParseException.class, () -> {
			parser.parse(new Tokenizer("a b"));
		});
	}

	@Test
	void testManyParser() throws Exception {
		Parser parser = new ManyParser(new IdentifierParser());
		ASTNode node = parser.parse(new Tokenizer("a"));
		assertNotNull(node);
		node = parser.parse(new Tokenizer("a b c"));
		assertNotNull(node);
	}
}
