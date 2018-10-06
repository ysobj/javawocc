package javawocc.ast;

interface Operator {
	Object evaluate(Integer lvalue, Integer rvalue);

	String compile();

	int getOrder();

	boolean isTypeRight();
}