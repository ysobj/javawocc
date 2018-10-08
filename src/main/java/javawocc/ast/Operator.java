package javawocc.ast;

import javawocc.model.Environment;

interface Operator {
	Object evaluate(Environment env, ASTNode first, ASTNode second);

	String compile(Environment env, ASTNode first, ASTNode second);

	int getOrder();

	boolean isTypeRight();
}