package com.mec.core;

import com.mec.util.Util;

public class Parameter {
	private Class<?> type;
	private Object value;
	
	Parameter() { }

	Parameter(String para) {
		if (para == null) {
			return;
		}
		if (para.startsWith("$") || para.startsWith("#")||para.startsWith("=")) {
			return;
		}
		
		type = Util.getType(para);
		value = Util.getValue(para);
	}
	
	Class<?> getType() {
		return type;
	}

	void setType(Class<?> type) {
		this.type = type;
	}

	Object getValue() {
		return value;
	}

	void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return (value == null ? "null" : value.toString()) 
				+ "(" + type.getName() + ")";
	}

}
