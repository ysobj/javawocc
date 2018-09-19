package javawocc.constant;

public class Constant {
	private int index;
	protected String content;

	public Constant() {
		content = "";
	}

	public Constant(String content) {
		super();
		this.content = content;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return this.content;
	}

}
