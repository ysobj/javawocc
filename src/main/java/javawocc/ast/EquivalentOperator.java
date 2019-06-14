package javawocc.ast;

import javawocc.model.Environment;

public class EquivalentOperator implements Operator {
	private static final int iload_0 = 26;
	private static final int iload_1 = 27;
	private static final int iload_2 = 28;
	private static final int iload_3 = 29;
	private static final int iload = 21;
	private static final int[] iload_specific = { iload_0, iload_1, iload_2, iload_3 };
	private static final int if_icmpeq = 159;
	private static final int if_icmpne = 160;
	private static final int if_icmplt = 161;
	private static final int if_icmpge = 162;
	private static final int if_icmpgt = 163;
	private static final int if_icmple = 164;
	private static final int iconst_m1 = 2;
	private static final int iconst_0 = 3;
	private static final int iconst_1 = 4;
	private static final int iconst_2 = 5;
	private static final int iconst_3 = 6;
	private static final int iconst_4 = 7;
	private static final int iconst_5 = 8;
	private static final int[] iconst_specific = { iconst_0, iconst_1, iconst_2, iconst_3, iconst_4, iconst_5 };

	@Override
	public Object evaluate(Environment env, ASTNode first, ASTNode second) {
		Object fvalue = first.evaluate(env);
		Object svalue = second.evaluate(env);
		if (fvalue == null) {
			if (svalue != null) {
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		return fvalue.equals(svalue);
	}

	@Override
	public String compile(Environment env, ASTNode first, ASTNode second) {
		StringBuilder sb = new StringBuilder();
		// left hand side should be identifier, and right hand side is a specific
		// number.
		Identifier left = (Identifier) first;
		// iload index
		sb.append(String.format("%02x", iload_specific[env.getIndex(left.getExpression())]));
		NumberLiteral right = (NumberLiteral) second;
		// iconst_x
		Number rvalue = (Number) right.evaluate(env);
		sb.append(String.format("%02x", iconst_specific[rvalue.intValue()]));
		return sb.toString();
	}

	@Override
	public String toString() {
		return "==";
	}

	@Override
	public int getOrder() {
		return 40;
	}

	@Override
	public boolean isTypeRight() {
		return true;
	}
}
