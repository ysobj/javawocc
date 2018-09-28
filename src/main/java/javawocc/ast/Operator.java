package javawocc.ast;

interface Operator {
	Object evaluate(Integer lvalue, Integer rvalue);

	String compile();
}