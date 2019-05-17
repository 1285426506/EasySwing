package com.mec.util;

import java.lang.reflect.Constructor;

public class Util {
	public static String getSetterName(String setterName) {
		if (setterName == null) {
			return null;
		}
		setterName = setterName.trim();
		if (setterName.startsWith("set")) {
			return setterName;
		}
		StringBuffer result = new StringBuffer("set");
		
		String name = new String(setterName);
		name = name.substring(0, 1).toUpperCase() 
				+ name.substring(1);
		result.append(name);
		
		return result.toString();
	}
	
	public static Class<?> getType(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		
		if (str.startsWith("'") && str.endsWith("'")) {
			return String.class;
		}
		if (str.endsWith("L")) {
			return long.class;
		}
		if (str.split(".").length == 1) {
			return double.class;
		}
		if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
			return boolean.class;
		}
		
		return int.class;
	}
	
	public static Object getValue(String str) {
		if (str.equalsIgnoreCase("null")) {
			return null;
		}
		str = str.trim();
		
		if (str.startsWith("'") && str.endsWith("'")) {
			return str.substring(1, str.length()-1);
		}
		if (str.endsWith("L")) {
			return Long.valueOf(str.substring(0, str.length()-1));
		}
		if (str.split(".").length == 1) {
			return Double.valueOf(str);
		}
		if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
			return str.equalsIgnoreCase("true");
		}
		
		return Integer.valueOf(str);
	}
	
	public static Object newInstance(Class<?> klass, Class<?>[] typs, Object[] paras) 
			throws Exception {
		Constructor<?> constructor = klass.getDeclaredConstructor(typs);
		return constructor.newInstance(paras);
	}
	
}
