package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.tokenizer.Tokenizer;

class MicroParserTest {

	@Test
	void test() {
		Parser parser = new MicroParser();
		assertEquals(12 + 34, parser.parse(new Tokenizer("12 + 34")).evaluate());
		assertEquals(12 + 34 + 34, parser.parse(new Tokenizer("12 + 34 + 34")).evaluate());
		// should be 114, but microparser has not implement order of operators yet.
		assertEquals((12 + 34 + 34) * 2, parser.parse(new Tokenizer("12 + 34 + 34 * 2")).evaluate());
	}

}
