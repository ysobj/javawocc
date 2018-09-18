package javawocc;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Sample {
	public static void main(String[] args) throws Exception {
		String bytes = //
				"cafebabe" // magic number(cafe babe)
						+ "0000" // minor version number of the class file format being used
						+ "0034" // major version number of the class file format being used.(Java SE 8 = 0x34)
						+ "001c" // Constant Pool Count(28)
						// Constant Pool --->
						+ "0a0006000f" // #1(0a = Method Ref #6, #15 "java/lang/Object" "<init>" "()V")
						+ "0900100011" // #2(09 = Field reference #16, #17 "java/lang/Stream" "out" "Ljava/io/PrintStream;")
						+ "030133efb6" // #3(03 = Integer 20180918)
						+ "0a00120013" // #4(0a = Method Ref #18, #19 "java/io/PrintStream" "println" "(I)V")
						+ "070014" // #5(07 = Class reference #20 "javawocc/HelloWorld")
						+ "070015" // #6(07 = Class reference #21 "java/lang/Stream")
						+ "0100063c696e69743e" // #7(01 = UTF-8 "<init>")
						+ "010003282956" // #8(01 = UTF-8 "()V")
						+ "010004436f6465" // #9(01 = UTF-8 "Code")
						+ "01000f4c696e654e756d6265725461626c65" // #10(01 = UTF-8 "LineNumberTable")
						+ "0100046d61696e" // #11(01 = UTF-8 "main")
						+ "010016285b4c6a6176612f6c616e672f537472696e673b2956" // #12(01 = UTF-8
																				// "([Ljava/lang/String;)V")
						+ "01000a536f7572636546696c65" // #13(01 = UTF-8 "SourceFile")
						+ "01000f48656c6c6f576f726c642e6a617661" // #14(01 = UTF-8 "HelloWorld.java")
						+ "0c00070008" // #15(0c = Name and Type #7, #8 "<init>" "()V")
						+ "070016" // #16(07 = Class reference #22 "java/lang/Stream")
						+ "0c00170018" // #17(0c = Name and Type #23, #24 "out" "Ljava/io/PrintStream;")
						+ "070019" // #18(07 = Class reference #25 "java/io/PrintStream")
						+ "0c001a001b" // #19(0c = Name and Type #26, #27 "println" "(I)V")
						+ "0100136a617661776f63632f48656c6c6f576f726c64" // #20(01 = UTF-8 "javawocc/HelloWorld")
						+ "0100106a6176612f6c616e672f4f626a656374" // #21(01 = UTF-8 "java/lang/Object")
						+ "0100106a6176612f6c616e672f53797374656d" // #22(01 = UTF-8 "java/lang/Stream")
						+ "0100036f7574" // #23(01 = UTF-8 "out")
						+ "0100154c6a6176612f696f2f5072696e7453747265616d3b" // #24(01 = UTF-8 "Ljava/io/PrintStream;")
						+ "0100136a6176612f696f2f5072696e7453747265616d" // #25(01 = UTF-8 "java/io/PrintStream")
						+ "0100077072696e746c6e" // #26(01 = UTF-8 "println")
						+ "01000428492956" // #27(01 = UTF-8 "(I)V")
						// ---> Constant Pool
						+ "0021" // access flags, a bitmask
						+ "0005" // this class #5
						+ "0006" // super class #6
						+ "0000" // interface count = 0
						+ "0000" // field count = 0
						+ "0002" // method count = 2
						// method[0]-->
						+ "0001" // method[0] access_flag
						+ "0007" // method[0] name_index
						+ "0008" // method[0] descriptor_index
						+ "0001" // method[0] attributes_count
						// method[0].attribute[0]
						// https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.7.3
						+ "0009" // attribute_name_index #9 (Code)
						+ "0000001d" // attribute_length
						+ "0001" // max_stack
						+ "0001" // max_locals
						+ "00000005" // code_length
						//code
						+ "2a" // aload_0
						+ "b7" + "0001" // invokespecial #1
						+ "b1" // return 
						// code
						+ "0000" // exception_table_length
						+ "0001" // attribute_count
						+ "000a" // attribute_name_index #10 (LineNumberTable)
						+ "00000006" // attribute_length
						+ "0001" // line_number_table_length
						+ "0000" // start_pc[0]
						+ "0002" // line_number[0]
						// method[1]-->
						+ "0009" // method[1] access_flag
						+ "000b" // method[1] name_index
						+ "000c" // method[1] descriptor_index
						+ "0001" // method[1] attributes_count
						// method[1].attribute[0]
						+ "0009" // attribute_name_index #9 (Code)
						+ "00000025" // attribute_length
						+ "0002" // max_stack
						+ "0001" // max_locals
						+ "00000009" // code_length
						// code
						+ "b2" + "0002" // getstatic #2
						+ "12" + "03" // ldc #3
						+ "b6" + "0004" // invokevirtual #4 
						+ "b1" // return
						// code
						+ "0000" // exception_table_length
						+ "0001" // attribute_count
						+ "000a" // attribute_name_index #10 (LineNumberTable)
						+ "0000000a" // attribute_length
						+ "0002" // line_number_table_length
						+ "0000" // start_pc[0]
						+ "0004" // line_number[0]
						+ "0008" // start_pc[1]
						+ "0005" // line_number[1]
						//
						+ "0001" // attribute_count
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
