package javawocc.ast;

import java.util.List;

public class OperatorPrecedenceResolver {

	public ASTNode resolve(List<ASTNode> list) {
		ASTNode[] arr = list.toArray(new ASTNode[] {});
		while (arr.length > 1) {
			arr = build(arr, 1);
		}
		return arr[0];

	}
	//
	// protected List<ASTNode> process(List<ASTNode> list) {
	// if (list.size() == 3) {
	// list.add(createBinaryExpression(list.remove(0), list.remove(0),
	// list.remove(0)));
	// return list;
	// }
	// if (list.size() < 5) {
	// throw new IllegalArgumentException();
	// }
	// OperatorNode lop = (OperatorNode) list.get(1);
	// OperatorNode rop = (OperatorNode) list.get(3);
	// if (lop.getOperator().getOrder() > rop.getOperator().getOrder()) {
	// ASTNode node = createBinaryExpression(list.remove(0), list.remove(0),
	// list.remove(0));
	// list.add(0, node);
	// } else if (lop.getOperator().getOrder() < rop.getOperator().getOrder()) {
	// List<ASTNode> sub1 = list.subList(0, 2);
	// List<ASTNode> sub2 = process(list.subList(2, list.size()));
	// list.clear();
	// list.addAll(sub1);
	// list.addAll(sub2);
	// } else {
	// if (lop.isTypeRight() && rop.isTypeRight()) {
	// ASTNode node = createBinaryExpression(list.remove(0), list.remove(0),
	// list.remove(0));
	// list.add(0, node);
	// } else {
	// List<ASTNode> sub1 = list.subList(0, 2);
	// List<ASTNode> sub2 = process(list.subList(2, list.size()));
	// list.clear();
	// list.addAll(sub1);
	// list.addAll(sub2);
	// }
	// }
	// return list;
	// }

	protected BinaryExpression createBinaryExpression(ASTNode[] arr, int position) {
		return new BinaryExpression(arr[position - 1], (OperatorNode) arr[position], arr[position + 1]);
	}

	protected ASTNode[] build(ASTNode[] arr, int pos) {
		OperatorNode left = getLeftOperator(arr, pos);
		OperatorNode right = getRightOperator(arr, pos);
		if (left.isHigherOrder(right)) {
			ASTNode[] newArr = new ASTNode[arr.length - 2];
			if (pos - 3 > 0) {
				//
				System.arraycopy(arr, 0, newArr, 0, pos - 3);
			}
			newArr[pos - 3] = createBinaryExpression(arr, pos - 2);
			if (pos + 2 <= arr.length) {
				System.arraycopy(arr, pos, newArr, pos - 2, arr.length - pos);
			}
			arr = newArr;
		} else {
			arr = build(arr, pos + 2);
		}
		return arr;
	}

	protected ASTNode process(ASTNode[] arr) {
		while (arr.length > 1) {
			arr = build(arr, 0);
		}
		return arr[0];
	}

	protected OperatorNode getLeftOperator(ASTNode[] arr, int pos) {
		OperatorNode left = NULL_OPERATOR;
		if (pos > 1) {
			left = (OperatorNode) arr[pos - 2];
		}
		return left;
	}

	protected OperatorNode getRightOperator(ASTNode[] arr, int pos) {
		OperatorNode right = NULL_OPERATOR;
		if (pos < arr.length) {
			right = (OperatorNode) arr[pos];
		}
		return right;
	}

	protected static OperatorNode NULL_OPERATOR = new OperatorNode("") {

		@Override
		public boolean isHigherOrder(OperatorNode other) {
			return false;
		}

		@Override
		public int getOrder() {
			return -1;
		}

	};
}
