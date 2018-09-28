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

}