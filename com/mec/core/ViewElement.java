 package com.mec.core;

public class ViewElement {
	private Class<?> type;
	private Object object;
	
	protected ViewElement() {
	}

	protected ViewElement(Class<?> type, Object object) {
		this.type = type;
		this.object = object;
	}

	protected Class<?> getType() {
		return type;
	}

	protected void setType(Class<?> type) {
		this.type = type;
	}

	protected Object getObject() {
		return object;
	}

	protected void setObject(Object object) {
		this.object = object;
	}

}
