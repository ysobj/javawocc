package javawocc.constant;

public class UTF8Constant extends Constant {

	public UTF8Constant(String content) {
		super(content);
	}

	@Override
	public String toString() {
		byte[] arr = content.getBytes();
		StringBuilder sb = new StringBuilder();
		sb.append("01"); // type
		sb.append(String.format("%04x", content.length()));
		for (byte b : arr) {
			sb.append(String.format("%x", b));
		}
		return sb.toString();
	}

}
