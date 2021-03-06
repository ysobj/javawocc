package javawocc.model;

import javawocc.ast.ASTNode;
import javawocc.constant.Constant;
import javawocc.constant.FieldRef;
import javawocc.constant.MethodRef;
import javawocc.constant.UTF8Constant;
import javawocc.parser.JavawoccParser;
import javawocc.parser.Parser;
import javawocc.tokenizer.Tokenizer;

public class MethodInfoBuilder {
	private UTF8Constant code;
	private UTF8Constant lineNumberTableConst;

	public MethodInfoBuilder(UTF8Constant code, UTF8Constant lineNumberTable) {
		super();
		this.code = code;
		this.lineNumberTableConst = lineNumberTable;
	}

	public MethodInfo build() {
		MethodInfo methodInfo = new MethodInfo();
		return methodInfo;
	}

	public MethodInfo createMethod0(Constant name, Constant descriptor, MethodRef m1) throws Exception {
		// method[0]-->
		String content = "0001" // method[0] access_flag
				+ String.format("%04x", name.getIndex()) // method[0] name_index
				+ String.format("%04x", descriptor.getIndex()) // method[0] descriptor_index
				+ "0001"; // method[0] attributes_count
		// method[0].attribute[0]
		// https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.7.3

		CodeAttributeInfo codeAttribute = new CodeAttributeInfo(code);
		codeAttribute.setMaxStack(1);
		codeAttribute.setMaxLocals(1);
		codeAttribute.setCode("2ab7" + String.format("%04x", m1.getIndex()) + "b1");
		LineNumberTableAttributeInfo lineNumberTable = new LineNumberTableAttributeInfo(lineNumberTableConst);
		lineNumberTable.add("0000", "0002");
		codeAttribute.addAttribute(lineNumberTable);

		MethodInfo method = new MethodInfo();
		method.content = content + codeAttribute.toString();
		return method;
	}

	public MethodInfo createMethod1(Constant name, Constant descriptor, String statement, FieldRef f1, MethodRef m2)
			throws Exception {
		String content = "0009" // method[1] access_flag
				+ String.format("%04x", name.getIndex()) // method[0] name_index
				+ String.format("%04x", descriptor.getIndex()) // method[0] descriptor_index
				+ "0001"; // method[1] attributes_count
		// method[1].attribute[0]

		CodeAttributeInfo codeAttribute = new CodeAttributeInfo(code);
		codeAttribute.setMaxStack(4);
		codeAttribute.setMaxLocals(4);
		codeAttribute.setCode( //
				"b2" + String.format("%04x", f1.getIndex()) // getstatic #2
						+ convertStatement(statement) + "b6" + String.format("%04x", m2.getIndex()) // invokevirtual #4
						+ "b1");
		LineNumberTableAttributeInfo lineNumberTable = new LineNumberTableAttributeInfo(lineNumberTableConst);
		lineNumberTable.add("0000", "0004");
		lineNumberTable.add("0008", "0005");
		codeAttribute.addAttribute(lineNumberTable);
		MethodInfo method = new MethodInfo();
		method.content = content + codeAttribute.toString();
		return method;
	}

	protected String convertStatement(String statement) throws Exception {
		Parser javawoccParser = new JavawoccParser();
		ASTNode node = javawoccParser.parse(new Tokenizer(statement));
		Environment env = new Environment();
		System.out.println(node.compile(env));
		return node.compile(env);
	}
}
