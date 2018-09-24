package javawocc.model;

import javawocc.constant.UTF8Constant;

public class LineNumberTableAttributeInfo extends AttributeInfo {
	private UTF8Constant name;
	private StringBuilder sb;
	private int lineNumberTableLength;

	public LineNumberTableAttributeInfo(UTF8Constant name) {
		super(name);
		this.name = name;
		this.sb = new StringBuilder();
	}

	public void add(String startPc, String lineNumber) {
		sb.append(startPc);
		sb.append(lineNumber);
		lineNumberTableLength++;
	}

	public String toString() {
		StringBuilder tmp = new StringBuilder();
		tmp.append(String.format("%04x", name.getIndex()));
		tmp.append(String.format("%08x", lineNumberTableLength * 4 + 2));
		tmp.append(String.format("%04x", lineNumberTableLength));
		tmp.append(sb.toString());
		return tmp.toString();
	}
}
