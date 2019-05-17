package com.mec.core;

import java.util.ArrayList;
import java.util.List;

public abstract class ParameterList {
	private List<Parameter> parameterList;
	
	
	public ParameterList() {
		parameterList = new ArrayList<>();
	}
	
	public abstract Parameter getTypeAndValue(String para);
	
	public ParameterList(String paraStr) {
		this();
		initWithParaString(paraStr);
	}
	
	public void setParameterList(String paraStr) {
		initWithParaString(paraStr);
	}
	
	private void initWithParaString(String paraStr) {
		if (paraStr == null) {
			return ;
		}
		if(paraStr.equalsIgnoreCase("*")) {
			return ;
		}
		
		String[] paras = paraStr.split(",");
		for (int i = 0; i < paras.length; i++) {
			Parameter parameter = null;
			String parameterString = paras[i].trim();
			if (parameterString.startsWith("=") 
					|| parameterString.startsWith("#")
					||parameterString.startsWith("$")) {
				parameter = getTypeAndValue(parameterString);
			} else {  
				parameter = new Parameter(paras[i]);
			}
			parameterList.add(parameter);
		}
	}
	
	Class<?>[] getTypeList() {
		int paraCount = parameterList.size();
		if(parameterList.size() == 0) {
			return new Class<?> [] {};
		}
		Class<?>[] result = new Class<?>[paraCount];
		for (int i = 0; i < paraCount; i++) {
			result[i] = parameterList.get(i).getType();
		}
		
		return result;
	}
	
	Object[] getValueList() {
		int paraCount = parameterList.size();
		
		if(parameterList.size() == 0) {
			return new Object [] {};
		}
		Object[] result = new Object[paraCount];
		for (int i = 0; i < paraCount; i++) {
			result[i] = parameterList.get(i).getValue();
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if (parameterList.size() <= 0) {
			return "无参";
		}
		
		result.append("参数:");
		for (Parameter parameter : parameterList) {
			result.append("\n")
			.append(parameter.getValue())
			.append(":(")
			.append(parameter.getType().getSimpleName())
			.append(")");
		}
		
		return result.toString();
	}
	
}
