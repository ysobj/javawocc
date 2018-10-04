package javawocc.ast;

class DivideOperator implements Operator {

	@Override
	public Object evaluate(Integer lvalue, Integer rvalue) {
		return lvalue / rvalue;
	}

	@Override
	public String compile() {
		return "6c";
	}

	@Override
	public String toString() {
		return "/";
	}

}