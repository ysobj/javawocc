package javawocc.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import javawocc.ast.ASTNode;
import javawocc.ast.ASTNodeList;
import javawocc.ast.OperatorPrecedenceResolver;
import javawocc.model.Environment;
import javawocc.parser.ParenthesesParser.Type;
import javawocc.tokenizer.Tokenizer;
import javawocc.tokenizer.Token.TokenType;

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
		ASTNode node = parser.parse(new Tokenizer("a = 1 + 2; a = a + 3;"));
		assertNotNull(node);
		assertEquals("(a = (1 + 2))(a = (a + 3))", node.toString());
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
//		ASTNode node = parser.parse(new Tokenizer("if(a == 3){b=5}"));
		assertNotNull(node);
		assertEquals("(a = 3)(b = 4)if(a == 3){b = 5}b", node.toString());
	}

	@Test
	void testIfStatement2() throws Exception {
		Parser factor = new ChoiceParser(new NumberParser(), new IdentifierParser()) {
			public String toString() {
				return "factor";
			}
		};
		Parser expression = new SequenceParser(factor, new OneToManyParser(new OperatorParser(), factor)) {

			@Override
			protected ASTNode build(ASTNodeList node) {
				List<ASTNode> list = node.getNodeList();
				list.addAll(((ASTNodeList) list.remove(1)).getNodeList());
				OperatorPrecedenceResolver resolver = new OperatorPrecedenceResolver();
				return resolver.resolve(list);
			}

		};
		Parser parenthesesExpression = new ParenthesesParser(Type.PAREN, expression);
		Parser statement = new SequenceParser(expression,
				new OneToManyParser(false, new TerminatorParser(), expression)) {
			@Override
			protected ASTNode build(ASTNodeList node) {
				return node;
			}

			@Override
			public String toString() {
				return "statement";
			}
		};
		Parser block = new ParenthesesParser(Type.BRACE, statement);
		Parser ifStatement = new SequenceParser(new MatchParser(TokenType.KEYWORD, "if"), parenthesesExpression, block);
		Tokenizer tokenizer = new Tokenizer("if(a == 3){b=5}");
		ASTNode node = ifStatement.parse(tokenizer);
		assertNotNull(node);
		assertFalse(tokenizer.hasNext());
	}
}
