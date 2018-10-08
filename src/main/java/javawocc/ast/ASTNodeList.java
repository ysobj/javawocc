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
		Object ret = null;
		if (list != null) {
			for (ASTNode astNode : list) {
				ret = astNode.evaluate(env);
			}
		}
		return ret;
	}

	@Override
	public String compile(Environment env) {
		StringBuilder sb = new StringBuilder();
		if (list != null) {
			for (ASTNode astNode : list) {
				sb.append(astNode.compile(env));
			}
		}
		return sb.toString();
	}

	public void add(ASTNode node) {
		this.list.add(node);
	}

	public List<ASTNode> getNodeList() {
		return list;
	}

	@Override
	public String toString() {
		if (list == null || list.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (ASTNode astNode : list) {
			String tmp = astNode.toString();
			if (tmp != null && tmp.length() > 0) {
				sb.append(tmp);
			}
		}
		return sb.toString();
	}

}
