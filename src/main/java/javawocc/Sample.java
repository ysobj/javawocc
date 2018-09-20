package javawocc;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javawocc.constant.ClassConstant;
import javawocc.constant.Constant;
import javawocc.constant.MethodRef;
import javawocc.constant.NameAndType;
import javawocc.constant.UTF8Constant;
import javawocc.model.ConstantPool;
import javawocc.model.MethodInfo;

public class Sample {
	public static void main(String[] args) throws Exception {
		int b = Integer.valueOf(args[1]);
		int c = Integer.valueOf(args[2]);
		ConstantPool cp = new ConstantPool();

		UTF8Constant u2 = new UTF8Constant("java/lang/Object");
		UTF8Constant u3 = new UTF8Constant("java/lang/System");
		UTF8Constant u4 = new UTF8Constant("java/io/PrintStream");

		UTF8Constant u7 = new UTF8Constant("<init>");
		UTF8Constant u8 = new UTF8Constant("()V");
		UTF8Constant u23 = new UTF8Constant("out");
		UTF8Constant u24 = new UTF8Constant("Ljava/io/PrintStream;");
		UTF8Constant u26 = new UTF8Constant("println");
		UTF8Constant u27 = new UTF8Constant("(I)V");

		ClassConstant c1 = new ClassConstant(u2);
		ClassConstant c2 = new ClassConstant(u4);
		NameAndType nt1 = new NameAndType(u7, u8);
		NameAndType nt2 = new NameAndType(u26, u27);
		cp.addConstant(new MethodRef(c1, nt1)); // #1(0a = Method Ref #6, #15 "java/lang/Object" "<init>" "()V")

		cp.addConstant(new Constant("0900100011")); // #2(09 = Field reference #16, #17 "java/lang/Stream" "out"
		// "Ljava/io/PrintStream;")
		cp.addConstant(new Constant("03" + String.format("%08x", b))); // #3(03 = Integer 20180918)
		cp.addConstant(new MethodRef(c2, nt2)); // #4(0a = Method Ref #18, #19 "java/io/PrintStream" "println"
												// "(I)V")
		UTF8Constant u1 = new UTF8Constant("javawocc/HelloWorld");
		cp.addConstant(new ClassConstant(u1)); // #5(07 = Class reference #20 "javawocc/HelloWorld")
		cp.addConstant(c1); // #6(07 = Class reference #21 "java/lang/Stream")

		cp.addConstant(u7); // #7(01 = UTF-8 "<init>")
		cp.addConstant(u8); // #8(01 = UTF-8 "()V")
		cp.addConstant(new UTF8Constant("Code")); // #9(01 = UTF-8 "Code")
		cp.addConstant(new UTF8Constant("LineNumberTable")); // #10(01 = UTF-8 "LineNumberTable")
		cp.addConstant(new UTF8Constant("main")); // #11(01 = UTF-8 "main")
		cp.addConstant(new UTF8Constant("([Ljava/lang/String;)V")); // #12(01 = UTF-8 "([Ljava/lang/String;)V")
		cp.addConstant(new UTF8Constant("SourceFile"));// #13(01 = UTF-8 "SourceFile")
		cp.addConstant(new UTF8Constant("HelloWorld.java")); // #14(01 = UTF-8 "HelloWorld.java")
		cp.addConstant(nt1); // #15(0c = Name and Type #7, #8 "<init>" "()V")
		cp.addConstant(new ClassConstant(u3)); // #16(07 = Class reference #22 "java/lang/System")
		cp.addConstant(new NameAndType(u23, u24)); // #17(0c = Name and Type #23, #24 "out" "Ljava/io/PrintStream;")
		cp.addConstant(c2); // #18(07 = Class reference #25 "java/io/PrintStream")
		cp.addConstant(nt2); // #19(0c = Name and Type #26, #27 "println" "(I)V")
		cp.addConstant(u1); // #20(01 = UTF-8 "javawocc/HelloWorld")
		cp.addConstant(u2); // #21(01 = UTF-8 "java/lang/Object")
		cp.addConstant(u3); // #22(01 = UTF-8 "java/lang/System")
		cp.addConstant(u23); // #23(01 = UTF-8 "out")
		cp.addConstant(u24); // #24(01 = UTF-8 "Ljava/io/PrintStream;")
		cp.addConstant(u4); // #25(01 = UTF-8
							// "java/io/PrintStream")
		cp.addConstant(u26); // #26(01 = UTF-8 "println")
		cp.addConstant(u27); // #27(01 = UTF-8 "(I)V")

		String bytes = //
				"cafebabe" // magic number(cafe babe)
						+ "0000" // minor version number of the class file format being used
						+ "0034" // major version number of the class file format being used.(Java SE 8 = 0x34)
						+ cp.toString() // Constant pool
						+ "0021" // access flags, a bitmask
						+ "0005" // this class #5
						+ "0006" // super class #6
						+ "0000" // interface count = 0
						+ "0000" // field count = 0
						+ "0002"; // method count = 2

		MethodInfo method0 = MethodInfo.createMethod0();
		bytes += method0.toString();
		MethodInfo method1 = MethodInfo.createMethod1(b, c);
		bytes += method1.toString();
		bytes +=

				//
				"0001" // attribute_count
						+ "000d" // attribute_name_index
						+ "00000002" // attribute_length
						+ "000e";// info[2]
		OutputStream os = new FileOutputStream(args[0] + "/javawocc/HelloWorld.class");
		os.write(decode(bytes));
		os.close();
	}

	private static byte[] decode(String bytes) throws Exception {
		byte[] result = new byte[bytes.length() / 2];
		for (int i = 0; i < bytes.length() / 2; i++) {
			result[i] = (byte) ((Character.digit(bytes.charAt(i * 2), 16)) << 4
					| (Character.digit(bytes.charAt(i * 2 + 1), 16)));
		}
		return result;
	}
}