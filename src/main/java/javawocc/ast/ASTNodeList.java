package javawocc.ast;

import java.util.ArrayList;
import java.util.List;

import javawocc.model.Environment;

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
	public Object evaluate(Environment env) {
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
