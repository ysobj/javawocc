package javawocc.model;

import javawocc.constant.Constant;
import javawocc.constant.MethodRef;
import javawocc.constant.UTF8Constant;

public class MessageInfoBuilder {
	private UTF8Constant code;
	private UTF8Constant lineNumberTableConst;

	public MessageInfoBuilder(UTF8Constant code, UTF8Constant lineNumberTable) {
		super();
		this.code = code;
		this.lineNumberTableConst = lineNumberTable;
	}

	public MethodInfo build() {
		MethodInfo methodInfo = new MethodInfo();
		return methodInfo;
	}

	public MethodInfo createMethod0(Constant name, Constant descriptor, Constant code, MethodRef m1) {
		// method[0]-->
		String content = "0001" // method[0] access_flag
				+ String.format("%04x", name.getIndex()) // method[0] name_index
				+ String.format("%04x", descriptor.getIndex()) // method[0] descriptor_index
				+ "0001" // method[0] attributes_count
				// method[0].attribute[0]
				// https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.7.3
				+ String.format("%04x", code.getIndex()) // attribute_name_index #9 (Code)
				+ "0000001d" // attribute_length
				+ "0001" // max_stack
				+ "0001" // max_locals
				+ "00000005" // code_length
				// code
				+ "2a" // aload_0
				+ "b7" + String.format("%04x", m1.getIndex()) // invokespecial #1
				+ "b1" // return
				// code
				+ "0000" // exception_table_length
				+ "0001"; // attribute_count
		LineNumberTableAttributeInfo lineNumberTable = new LineNumberTableAttributeInfo(lineNumberTableConst);
		lineNumberTable.add("0000", "0002");

		MethodInfo method = new MethodInfo();
		method.content = content + lineNumberTable.toString();
		return method;
	}

	public MethodInfo createMethod1(int b, int c) {
		String content = "0009" // method[1] access_flag
				+ "000b" // method[1] name_index
				+ "000c" // method[1] descriptor_index
				+ "0001" // method[1] attributes_count
				// method[1].attribute[0]
				+ "0009" // attribute_name_index #9 (Code)
				+ "0000002a" // attribute_length
				+ "0003" // max_stack
				+ "0001" // max_locals
				+ "0000000e" // *code_length
				// code
				+ "b2" + "0002" // getstatic #2
				+ "11" + String.format("%04x", b) // sipush b
				+ "11" + String.format("%04x", c) // sipush c
				+ "60" // iadd
				+ "b6" + "0004" // invokevirtual #4
				+ "b1" // return
				// code
				+ "0000" // exception_table_length
				+ "0001"; // attribute_count
		//
		LineNumberTableAttributeInfo lineNumberTable = new LineNumberTableAttributeInfo(lineNumberTableConst);
		lineNumberTable.add("0000", "0004");
		lineNumberTable.add("0008", "0005");
		MethodInfo method = new MethodInfo();
		method.content = content + lineNumberTable.toString();
		return method;
	}
}
