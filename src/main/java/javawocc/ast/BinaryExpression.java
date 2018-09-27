package javawocc.ast;

public class BinaryExpression extends ASTNode {
	private ASTNode left;
	private ASTNode right;
	private String operator;

	public BinaryExpression(ASTNode left, String operator, ASTNode right) {
		super();
		this.left = left;
		this.operator = operator;
		this.right = right;
	}

	@Override
	public Object evaluate() {
		Integer lvalue = (Integer) left.evaluate();
		Integer rvalue = (Integer) right.evaluate();
		if ("+".equals(operator)) {
			return lvalue + rvalue;
		} else if ("-".equals(operator)) {
			return lvalue - rvalue;
		}
		return null;
	}

	@Override
	public String compile() {
		StringBuilder sb = new StringBuilder();
		sb.append("11"); // sipush
		sb.append(String.format("%04x", left.evaluate()));
		sb.append("11"); // sipush
		sb.append(String.format("%04x", right.evaluate()));
		if ("+".equals(operator)) {
			sb.append("60");
		} else if ("-".equals(operator)) {
			sb.append("64");
		}
		return sb.toString();
	}

}
