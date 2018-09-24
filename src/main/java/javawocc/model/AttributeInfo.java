package javawocc.model;

import javawocc.constant.UTF8Constant;

public class AttributeInfo {
	// u2 attribute_name_index;
	// u4 attribute_length;
	// u1 info[attribute_length];
	protected UTF8Constant attributeName;

	public AttributeInfo(UTF8Constant attributeName) {
		super();
		this.attributeName = attributeName;
	}
	
}