package javawocc.ast;

public class WhileNode extends ASTNode {
	private ASTNode condition;
	private ASTNode block;

	public WhileNode(ASTNode condition, ASTNode block) {
		super(null);
		this.condition = condition;
		this.block = block;
	}

	@Override
	public String toString() {
		return String.format("while(%s){%s}", this.condition, this.block);
	}
}
