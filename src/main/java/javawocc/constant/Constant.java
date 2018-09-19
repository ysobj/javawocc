package javawocc.constant;

public class Constant {
	private int index;
	private String content;
	
	public Constant(String content) {
		super();
		this.content = content;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return this.content;
	}

}
