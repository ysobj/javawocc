package javawocc.ast;

import javawocc.model.Environment;

class LessOperator implements Operator {

	@Override
	public Object evaluate(Environment env, ASTNode first, ASTNode second) {
		Number f = (Number) first.evaluate(env);
		Number s = (Number) second.evaluate(env);
		return f.intValue() < s.intValue();
	}

	@Override
	public String compile(Environment env, ASTNode first, ASTNode second) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "<";
	}

	@Override
	public int getOrder() {
		return 45;
	}

	@Override
	public boolean isTypeRight() {
		return false;
	}

}