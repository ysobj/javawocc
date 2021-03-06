package javawocc.ast;

import javawocc.model.Environment;

class MinusOperator implements Operator {

	@Override
	public Object evaluate(Environment env, ASTNode first, ASTNode second) {
		Number f = (Number) first.evaluate(env);
		Number s = (Number) second.evaluate(env);
		return f.intValue() - s.intValue();
	}

	@Override
	public String compile(Environment env, ASTNode first, ASTNode second) {
		StringBuilder sb = new StringBuilder();
		sb.append(first.compile(env));
		sb.append(second.compile(env));
		sb.append("64");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "-";
	}

	@Override
	public int getOrder() {
		return 50;
	}

	@Override
	public boolean isTypeRight() {
		// TODO Auto-generated method stub
		return false;
	}

}