package javawocc.ast;

import javawocc.model.Environment;

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
		case "=":
			this.operator = new AssignOperator();
			break;
		default:
			operator = null;
		}
	}

	@Override
	public Object evaluate(Environment env) {
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

	public int getOrder() {
		return this.getOperator().getOrder();
	}

	@Override
	public String toString() {
		return operator.toString();
	}

	public boolean isHigherOrder(OperatorNode other) {
		if (this.getOrder() > other.getOrder()) {
			return true;
		}
		if (this.getOrder() == other.getOrder()){
			return !this.isTypeRight();
		}
		return false;
	}

	public boolean isTypeRight() {
		return operator.isTypeRight();
	}
}
