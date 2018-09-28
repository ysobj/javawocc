package javawocc.ast;

public class BinaryExpression extends ASTNode {
	private ASTNode left;
	private ASTNode right;
	private Operator operator;

	public BinaryExpression(ASTNode left, String operator, ASTNode right) {
		super();
		this.left = left;
		switch (operator) {
		case "+":
			this.operator = new PlusOperator();
			break;
		case "-":
			this.operator = new MinusOperator();
			break;
		case "*":
			this.operator = new MultiplyOperator();
			break;
		case "/":
			this.operator = new DivideOperator();
			break;

		default:
			break;
		}
		this.right = right;
	}

	@Override
	public Object evaluate() {
		Integer lvalue = (Integer) left.evaluate();
		Integer rvalue = (Integer) right.evaluate();
		return operator.evaluate(lvalue, rvalue);
	}

	@Override
	public String compile() {
		StringBuilder sb = new StringBuilder();
		sb.append("11"); // sipush
		sb.append(String.format("%04x", left.evaluate()));
		sb.append("11"); // sipush
		sb.append(String.format("%04x", right.evaluate()));
		sb.append(operator.compile());
		return sb.toString();
	}

	private interface Operator {
		Object evaluate(Integer lvalue, Integer rvalue);

		String compile();
	}

	private class PlusOperator implements Operator {

		@Override
		public Object evaluate(Integer lvalue, Integer rvalue) {
			return lvalue + rvalue;
		}

		@Override
		public String compile() {
			return "60";
		}

	}

	private class MinusOperator implements Operator {

		@Override
		public Object evaluate(Integer lvalue, Integer rvalue) {
			return lvalue - rvalue;
		}

		@Override
		public String compile() {
			return "64";
		}

	}

	private class DivideOperator implements Operator {

		@Override
		public Object evaluate(Integer lvalue, Integer rvalue) {
			return lvalue / rvalue;
		}

		@Override
		public String compile() {
			return "6c";
		}

	}

	private class MultiplyOperator implements Operator {

		@Override
		public Object evaluate(Integer lvalue, Integer rvalue) {
			return lvalue * rvalue;
		}

		@Override
		public String compile() {
			return "68";
		}

	}
}
