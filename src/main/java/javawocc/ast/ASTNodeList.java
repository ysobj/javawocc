package javawocc.ast;

import java.util.ArrayList;
import java.util.List;

public class ASTNodeList extends ASTNode {
	private List<ASTNode> list;

	public ASTNodeList(List<ASTNode> list) {
		super();
		this.list = list;
	}

	public ASTNodeList() {
		this(new ArrayList<>());
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String compile() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(ASTNode node) {
		this.list.add(node);
	}

	public List<ASTNode> getNodeList(){
		return list;
	}
}
