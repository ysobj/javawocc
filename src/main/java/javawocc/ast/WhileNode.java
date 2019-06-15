package javawocc.ast;

import javawocc.model.Environment;

public class WhileNode extends ASTNode {
	private ASTNode condition;
	private ASTNode block;

	public WhileNode(ASTNode condition, ASTNode block) {
		super(null);
		this.condition = condition;
		this.block = block;
	}

	@Override
	public Object evaluate(Environment env) {
		while((Boolean)this.condition.evaluate(env)) {
			this.block.evaluate(env);
		}
		return new NullNode();
	}

	@Override
	public String toString() {
		return String.format("while(%s){%s}", this.condition, this.block);
	}
}
