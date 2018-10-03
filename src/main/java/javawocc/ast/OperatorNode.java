package javawocc.ast;

public class OperatorNode extends ASTNode {
	private Operator operator;

	public OperatorNode(String operator) {
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
			operator = null;
		}
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String compile() {
		return this.operator.compile();
	}

	public Operator getOperator() {
		return this.operator;
	}

	@Override
	public String toString() {
		return operator.toString();
	}
}
