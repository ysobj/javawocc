package javawocc.ast;

import javawocc.model.Environment;

public class BinaryExpression extends ASTNode {
	private ASTNode left;
	private ASTNode right;
	private OperatorNode operator;

	public BinaryExpression(ASTNode left, OperatorNode operator, ASTNode right) {
		super();
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	@Override
	public Object evaluate(Environment env) {
		return operator.getOperator().evaluate(env, left, right);
	}

	@Override
	public String compile() {
		StringBuilder sb = new StringBuilder();
		sb.append(left.compile());
		sb.append(right.compile());
		sb.append(operator.compile());
		return sb.toString();
	}

	@Override
	public String toString() {
		return "(" + left.toString() + " " + operator.toString() + " " + right.toString() + ")";
	}
}
