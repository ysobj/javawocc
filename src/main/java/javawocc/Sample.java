package javawocc;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javawocc.model.ConstantPool;
import javawocc.model.MethodInfo;

public class Sample {
	public static void main(String[] args) throws Exception {
		int b = Integer.valueOf(args[1]);
		int c = Integer.valueOf(args[2]);
		ConstantPool cp = new ConstantPool();
		String bytes = //
				"cafebabe" // magic number(cafe babe)
						+ "0000" // minor version number of the class file format being used
						+ "0034" // major version number of the class file format being used.(Java SE 8 = 0x34)
						+ cp.toString()
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