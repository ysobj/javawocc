package javawocc.model;

import javawocc.constant.Constant;
import javawocc.constant.UTF8Constant;

public class SourceFileAttribute extends AttributeInfo {
	private Constant sourceFileConstant;

	public SourceFileAttribute(UTF8Constant attributeName) {
		super(attributeName);
	}

	public void setSourceFileConstant(Constant sourceFileConstant) {
		this.sourceFileConstant = sourceFileConstant;
	}

	@Override
	public String toString() {
		return String.format("%04x", attributeName.getIndex()) // attribute_name_index
				+ "00000002" // attribute_length
				+ String.format("%04x", sourceFileConstant.getIndex());// info[2]
	}

}
