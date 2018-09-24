package javawocc.model;

import java.util.ArrayList;
import java.util.List;

import javawocc.constant.UTF8Constant;

public class CodeAttributeInfo extends AttributeInfo {
	protected List<AttributeInfo> attributes;
	protected LineNumberTableAttributeInfo lineNumberTableAttributeInfo;
	protected String code;
	protected int maxStack;
	protected int maxLocals;

	public void setMaxStack(int maxStack) {
		this.maxStack = maxStack;
	}

	public void setMaxLocals(int maxLocals) {
		this.maxLocals = maxLocals;
	}

	public CodeAttributeInfo(UTF8Constant attributeName) {
		super(attributeName);
		this.attributes = new ArrayList<>();
	}

	public void addAttribute(AttributeInfo attribute) {
		this.attributes.add(attribute);
	}

	public void setLineNumberTableAttributeInfo(LineNumberTableAttributeInfo lineNumberTableAttributeInfo) {
		this.lineNumberTableAttributeInfo = lineNumberTableAttributeInfo;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%04x", this.attributeName.getIndex()));// u2 attribute_name_index;
		sb.append(String.format("%08x", 0));// u4 attribute_length;
		sb.append(String.format("%04x", this.maxStack));// u2 max_stack;
		sb.append(String.format("%04x", this.maxLocals));// u2 max_locals;
		sb.append(String.format("%08x", this.code.length() / 2)); // u4 code_length;
		sb.append(this.code); // u1 code[code_length];
		sb.append(String.format("%04x", 0));// u2 exception_table_length;
		// { u2 start_pc;
		// u2 end_pc;
		// u2 handler_pc;
		// u2 catch_type;
		// } exception_table[exception_table_length];
		sb.append(String.format("%04x", this.attributes.size())); // u2 attributes_count;
		attributes.forEach(a -> sb.append(a.toString()));// attribute_info attributes[attributes_count];
		String x = String.format("%08x", sb.length() / 2 - 6);
		sb.replace(4, 12, x);
		return sb.toString();
	}

}