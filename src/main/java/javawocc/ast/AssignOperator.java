package javawocc.ast;

import javawocc.model.Environment;

public class AssignOperator implements Operator {

	@Override
	public Object evaluate(Environment env, ASTNode first, ASTNode second) {
		Object value = second.evaluate(env);
		env.put(first.toString(), value);
		return value;
	}

	@Override
	public String compile(Environment env, ASTNode first, ASTNode second) {
		StringBuilder sb = new StringBuilder();
		sb.append(second.compile(env));
		sb.append("3c"); // istore_1 TODO adhoc implementation
		return sb.toString();
	}

	@Override
	public int getOrder() {
		return 30;
	}

	@Override
	public boolean isTypeRight() {
		return true;
	}

	@Override
	public String toString() {
		return "=";
	}
}
