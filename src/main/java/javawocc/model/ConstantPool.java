package javawocc.model;

import java.util.ArrayList;
import java.util.List;

import javawocc.constant.Constant;

public class ConstantPool {
	private List<Constant> constants = new ArrayList<>();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%04x", constants.size() + 1));
		for (Constant constant : constants) {
			sb.append(constant.toString());
		}
		return sb.toString();
	}

	public <T extends Constant> T addConstant(T constant) {
		constant.setIndex(constants.size() + 1);
		constants.add(constant);
		return constant;
	}
}
