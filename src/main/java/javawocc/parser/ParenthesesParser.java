package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.tokenizer.Tokenizer;

public class ParenthesesParser extends SequenceParser {
	
	public enum BracketType {
		PARENTHESES, BRACES
	}
}
