package javawocc;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javawocc.constant.Constant;
import javawocc.model.ConstantPool;
import javawocc.model.MethodInfo;

public class Sample {
	public static void main(String[] args) throws Exception {
		int b = Integer.valueOf(args[1]);
		int c = Integer.valueOf(args[2]);
		ConstantPool cp = new ConstantPool();

		cp.addConstant(new Constant("0a0006000f")); // #1(0a = Method Ref #6, #15 "java/lang/Object" "<init>" "()V")

		cp.addConstant(new Constant("0900100011")); // #2(09 = Field reference #16, #17 "java/lang/Stream" "out"
		// "Ljava/io/PrintStream;")
		cp.addConstant(new Constant("03" + String.format("%08x", b))); // #3(03 = Integer 20180918)
		cp.addConstant(new Constant("0a00120013")); // #4(0a = Method Ref #18, #19 "java/io/PrintStream" "println"
													// "(I)V")
		cp.addConstant(new Constant("070014")); // #5(07 = Class reference #20 "javawocc/HelloWorld")
		cp.addConstant(new Constant("070015")); // #6(07 = Class reference #21 "java/lang/Stream")
		cp.addConstant(new Constant("0100063c696e69743e")); // #7(01 = UTF-8 "<init>")
		cp.addConstant(new Constant("010003282956")); // #8(01 = UTF-8 "()V")
		cp.addConstant(new Constant("010004436f6465")); // #9(01 = UTF-8 "Code")
		cp.addConstant(new Constant("01000f4c696e654e756d6265725461626c65")); // #10(01 = UTF-8 "LineNumberTable")
		cp.addConstant(new Constant("0100046d61696e")); // #11(01 = UTF-8 "main")
		cp.addConstant(new Constant("010016285b4c6a6176612f6c616e672f537472696e673b2956")); // #12(01 = UTF-8
		// "([Ljava/lang/String;)V")
		cp.addConstant(new Constant("01000a536f7572636546696c65")); // #13(01 = UTF-8 "SourceFile")
		cp.addConstant(new Constant("01000f48656c6c6f576f726c642e6a617661")); // #14(01 = UTF-8 "HelloWorld.java")
		cp.addConstant(new Constant("0c00070008")); // #15(0c = Name and Type #7, #8 "<init>" "()V")
		cp.addConstant(new Constant("070016")); // #16(07 = Class reference #22 "java/lang/Stream")
		cp.addConstant(new Constant("0c00170018")); // #17(0c = Name and Type #23, #24 "out" "Ljava/io/PrintStream;")
		cp.addConstant(new Constant("070019")); // #18(07 = Class reference #25 "java/io/PrintStream")
		cp.addConstant(new Constant("0c001a001b")); // #19(0c = Name and Type #26, #27 "println" "(I)V")
		cp.addConstant(new Constant("0100136a617661776f63632f48656c6c6f576f726c64")); // #20(01 = UTF-8
																						// "javawocc/HelloWorld")
		cp.addConstant(new Constant("0100106a6176612f6c616e672f4f626a656374")); // #21(01 = UTF-8 "java/lang/Object")
		cp.addConstant(new Constant("0100106a6176612f6c616e672f53797374656d")); // #22(01 = UTF-8 "java/lang/Stream")
		cp.addConstant(new Constant("0100036f7574")); // #23(01 = UTF-8 "out")
		cp.addConstant(new Constant("0100154c6a6176612f696f2f5072696e7453747265616d3b")); // #24(01 = UTF-8
																							// "Ljava/io/PrintStream;")
		cp.addConstant(new Constant("0100136a6176612f696f2f5072696e7453747265616d")); // #25(01 = UTF-8
																						// "java/io/PrintStream")
		cp.addConstant(new Constant("0100077072696e746c6e")); // #26(01 = UTF-8 "println")
		cp.addConstant(new Constant("01000428492956")); // #27(01 = UTF-8 "(I)V")

		String bytes = //
				"cafebabe" // magic number(cafe babe)
						+ "0000" // minor version number of the class file format being used
						+ "0034" // major version number of the class file format being used.(Java SE 8 = 0x34)
						+ cp.toString() + "0021" // access flags, a bitmask
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