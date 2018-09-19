package javawocc.model;

public class ConstantPool {
	private int size = 28;

	@Override
	public String toString() {
		int a = 123;

		String x = String.format("%04x", size) // Constant Pool Count(29)
				// Constant Pool --->
				+ "0a0006000f" // #1(0a = Method Ref #6, #15 "java/lang/Object" "<init>" "()V")
				+ "0900100011" // #2(09 = Field reference #16, #17 "java/lang/Stream" "out"
								// "Ljava/io/PrintStream;")
				+ "03" + String.format("%08x", a) // #3(03 = Integer 20180918)
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
				+ "01000428492956"; // #27(01 = UTF-8 "(I)V")
		return x;
	}

}
