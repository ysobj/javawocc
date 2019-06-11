package javawocc.ast;

import javawocc.model.Environment;

public class IfNode extends ASTNode {
	private ASTNode condition;
	private ASTNode block;
	private ASTNode elseBlock;

	public IfNode(ASTNode condition, ASTNode block) {
		this(condition, block, null);
	}

	public IfNode(ASTNode condition, ASTNode block, ASTNode elseBlock) {
		super(null);
		this.condition = condition;
		this.block = block;
		this.elseBlock = elseBlock;
	}

	@Override
	public Object evaluate(Environment env) {
		Object res = this.condition.evaluate(env);
		if (res instanceof Boolean) {
			if ((Boolean) res) {
				this.block.evaluate(env);
			} else {
				if (this.elseBlock != null) {
					this.elseBlock.evaluate(env);
				}
			}
		} else {
			// TODO should create specific Exception?
			throw new IllegalArgumentException();
		}
		return new NullNode();
	}

	@Override
	public String toString() {
		if (this.elseBlock == null) {
			return String.format("if(%s){%s}", this.condition, this.block);
		} else {
			return String.format("if(%s){%s}else{%s}", this.condition, this.block, this.elseBlock);
		}
	}

}
