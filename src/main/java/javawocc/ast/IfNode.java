package javawocc.ast;

import javawocc.model.Environment;

public class IfNode extends ASTNode {
	private ASTNode condition;
	private ASTNode block;

	public IfNode(ASTNode condition, ASTNode block) {
		super(null);
		this.condition = condition;
		this.block = block;
	}

	@Override
	public Object evaluate(Environment env) {
		Object res = this.condition.evaluate(env);
		if (res instanceof Boolean) {
			if ((Boolean) res) {
				this.block.evaluate(env);
			}
		} else {
			// TODO should create specific Exception?
			throw new IllegalArgumentException();
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO
		return "if((a == 3)){(b = 5)}";
	}

}
