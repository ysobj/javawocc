package javawocc.ast;

class MinusOperator implements Operator {

	@Override
	public Object evaluate(Integer lvalue, Integer rvalue) {
		return lvalue - rvalue;
	}

	@Override
	public String compile() {
		return "64";
	}

	@Override
	public String toString() {
		return "-";
	}

}