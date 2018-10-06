package javawocc.ast;

class MultiplyOperator implements Operator {

	@Override
	public Object evaluate(Integer lvalue, Integer rvalue) {
		return lvalue * rvalue;
	}

	@Override
	public String compile() {
		return "68";
	}

	@Override
	public String toString() {
		return "*";
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