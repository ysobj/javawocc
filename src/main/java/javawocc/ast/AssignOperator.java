package javawocc.ast;

public class AssignOperator implements Operator {

	@Override
	public Object evaluate(Integer lvalue, Integer rvalue) {
		return null;
	}

	@Override
	public String compile() {
		return null;
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
