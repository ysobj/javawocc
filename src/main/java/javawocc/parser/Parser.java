package javawocc.parser;

import javawocc.ast.ASTNode;
import javawocc.tokenizer.Tokenizer;

public interface Parser {
	ASTNode parse(Tokenizer tokenizer) throws ParseException;
}
