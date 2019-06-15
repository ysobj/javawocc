package javawocc.ast;

import javawocc.model.Environment;

interface Operator {
	Object evaluate(Environment env, ASTNode first, ASTNode second);

	String compile(Environment env, ASTNode first, ASTNode second);

//  * / 80
//	+ - 50
//	< 45
//	== 40
//	= 30
	int getOrder();

	boolean isTypeRight();
}