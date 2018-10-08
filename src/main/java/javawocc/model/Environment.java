package javawocc.model;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	private Map<String, Object> map = new HashMap<>();
	private Map<String, Integer> keyIndex = new HashMap<>();
	private int index = 1;

	public Environment() {

	}

	public void put(String key, Object value) {
		if (!keyIndex.containsKey(key)) {
			keyIndex.put(key, index++);
		}
		map.put(key, value);
	}

	public Object get(String key) {
		return map.get(key);
	}

	public int getIndex(String key) {
		if(!keyIndex.containsKey(key)) {
			keyIndex.put(key, index++);
		}
		Integer tmp = keyIndex.get(key);
		return tmp.intValue();
	}
}
