package javawocc.ast;

import javawocc.model.Environment;

public class EquivalentOperator implements Operator {
	@Override
	public Object evaluate(Environment env, ASTNode first, ASTNode second) {
		Object fvalue = first.evaluate(env);
		Object svalue = second.evaluate(env);
		if (fvalue == null) {
			if (svalue != null) {
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		return fvalue.equals(svalue);
	}

	@Override
	public String compile(Environment env, ASTNode first, ASTNode second) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "==";
	}

	@Override
	public int getOrder() {
		return 40;
	}

	@Override
	public boolean isTypeRight() {
		return true;
	}
}
