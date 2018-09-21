package javawocc.model;

import java.util.List;

public class MethodInfo {
	public String accessFlags;
	public String nameIndex;
	public String descriptorIndex;
	public String attributesCount;
	public List<AttributeInfo> attributes;
	public String content;

	@Override
	public String toString() {
		return content;
	}

}