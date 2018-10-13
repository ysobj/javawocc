package javawocc.ast;

import javawocc.model.Environment;

public class BinaryExpression extends ASTNode {
	private ASTNode left;
	private ASTNode right;
	private OperatorNode operator;

	public BinaryExpression(ASTNode left, OperatorNode operator, ASTNode right) {
		super(null);
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	@Override
	public Object evaluate(Environment env) {
		return operator.getOperator().evaluate(env, left, right);
	}

	@Override
	public String compile(Environment env) {
		return operator.getOperator().compile(env, left, right);
	}

	@Override
	public String toString() {
		return "(" + left.toString() + " " + operator.toString() + " " + right.toString() + ")";
	}
}
