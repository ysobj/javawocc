package javawocc.ast;

import javawocc.model.Environment;

class DivideOperator implements Operator {

	@Override
	public Object evaluate(Environment env, ASTNode first, ASTNode second) {
		Number f = (Number) first.evaluate(env);
		Number s = (Number) second.evaluate(env);
		return f.intValue() / s.intValue();
	}

	@Override
	public String compile() {
		return "6c";
	}

	@Override
	public String toString() {
		return "/";
	}

	@Override
	public int getOrder() {
		return 80;
	}

	@Override
	public boolean isTypeRight() {
		// TODO Auto-generated method stub
		return false;
	}

}