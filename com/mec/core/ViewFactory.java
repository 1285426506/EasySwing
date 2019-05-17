package com.mec.core;

import java.util.HashMap;
import java.util.Map;

import com.mec.exception.ViewElementAleardyExistException;

public class ViewFactory {
	private String id;
	private Map<String, ViewElement> viewMap;
	private ViewFactory parent;
	
	protected ViewFactory() {
		viewMap = new HashMap<>();
	}

	protected void addElement(String id, ViewElement viewElement) 
			throws Exception {
		ViewElement element = viewMap.get(id);
		if (element != null) {
			throw new ViewElementAleardyExistException("¿Ø¼þ(" + id + ")ÒÑ´æÔÚ£¡");
		}
		viewMap.put(id, viewElement);
	}
	
	protected String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected void setParent(ViewFactory parent) {
		this.parent = parent;
	}

	
	
	public Map<String, ViewElement> getViewMap() {
		return viewMap;
	}

	@SuppressWarnings("unchecked")
	public <T> T getElement(String id) {
		ViewElement viewElement = viewMap.get(id);
		if (viewElement != null) {
			return (T) viewElement.getObject();
		}
		if (parent == null) {
			return null;
		}
		
		return parent.getElement(id);
	}
	
}
