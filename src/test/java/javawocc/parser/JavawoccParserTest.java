package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.model.Environment;
import javawocc.tokenizer.Tokenizer;

class JavawoccParserTest {

	@Test
	void testJavawoccParser() throws Exception {
		JavawoccParser parser = new JavawoccParser();
		ASTNode node = parser.parse(new Tokenizer("a = 1 + 2"));
		assertNotNull(node);
		assertEquals("(a = (1 + 2))", node.toString());
		Environment env = new Environment();
		node.evaluate(env);
		assertEquals(3, env.get("a"));
	}

	@Test
	void testMultipleStatementAndVariable() throws Exception {
		JavawoccParser parser = new JavawoccParser();
		ASTNode node = parser.parse(new Tokenizer("a = 1 + 2; a = a + 3;a"));
		assertNotNull(node);
		assertEquals("(a = (1 + 2))(a = (a + 3))a", node.toString());
		StringBuilder expected = new StringBuilder();
		// sipush 1
		expected.append("110001");
		// sipush 2
		expected.append("110002");
		// iadd
		expected.append("60");
		// istore_1
		expected.append("3c");
		//
		// iload_1
		expected.append("1b");
		// sipush 3
		expected.append("110003");
		// iadd
		expected.append("60");
		// istore_1
		expected.append("3c");
		// iload_1
		expected.append("1b");

		assertEquals(expected.toString(), node.compile(new Environment()));

		Environment env = new Environment();
		node.evaluate(env);
		assertEquals(6, env.get("a"));
	}

	@Test
	void testMultipleStatementAndVariable2() throws Exception {
		JavawoccParser parser = new JavawoccParser();
		ASTNode node = parser.parse(new Tokenizer("a = 1 + 2; b = a + 3; c = b * 4; c"));
		assertNotNull(node);
		assertEquals("(a = (1 + 2))(b = (a + 3))(c = (b * 4))c", node.toString());
		Environment env = new Environment();
		Object actual = node.evaluate(env);
		StringBuilder expected = new StringBuilder();
		// 110001
		expected.append("110001");
		// 110002
		expected.append("110002");
		// 60 iadd
		expected.append("60");
		// 3c istore_1
		expected.append("3c");

		// 1b iload_1
		expected.append("1b");
		// 110003
		expected.append("110003");
		// 60 iadd
		expected.append("60");
		// 3d istore_2
		expected.append("3d");

		// 1c iload_2
		expected.append("1c");
		// 110004
		expected.append("110004");
		// 68 imul
		expected.append("68");
		// 3e istore_3
		expected.append("3e");

		// 1d iload_3
		expected.append("1d");

		assertEquals(6, env.get("b"));
		assertEquals(24, actual);
		assertEquals(expected.toString(), node.compile(new Environment()));
	}

	@Test
	void testIfStatement() throws Exception {
		JavawoccParser parser = new JavawoccParser();
		ASTNode node = parser.parse(new Tokenizer("a = 3; b=4;if(a == 3){b=5}b"));
		assertNotNull(node);
		assertEquals("(a = 3)(b = 4)if(a == 3){b = 5}b", node.toString());
	}
}
