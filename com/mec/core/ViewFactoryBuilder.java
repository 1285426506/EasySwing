package com.mec.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mec.exception.IllegalParameterException;
import com.mec.exception.ViewTypeNotExistException;
import com.mec.model.IContainer;
import com.mec.util.PropertiesReader;
import com.mec.util.Util;
import com.mec.util.XMLReader;

public class ViewFactoryBuilder {
	private static final Map<String, ViewFactory> viewFactoryMap;
	
	static {
		viewFactoryMap = new HashMap<>();
		PropertiesReader.init("/easy_swing.config.properties");
		
	}
	
	public static ViewFactory load(InputStream is) {
		if (is == null) {
			return null;
		}
		
		ViewFactory viewFactory = new ViewFactory();
		new XMLReader() {
			@Override
			public void dealElement(Element element, int index) {
				String viewId = element.getAttribute("id");
				String viewParent = element.getAttribute("parent");
				viewFactory.setId(viewId);
				if (viewParent != null) {
					ViewFactory parent = viewFactoryMap.get(viewParent);
					viewFactory.setParent(parent);
				}
				
				try {
					dealController(element, viewFactory);
					viewFactoryMap.put(viewId, viewFactory);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.dealElementByTagName(XMLReader.openDocument(is), "view");
		
		return viewFactory;
	}
	
	private static void dealController(Element element, ViewFactory viewFactory)
			throws Exception {
		new XMLReader() {
			@Override
			public void dealElement(Element element, int index) {
				String id = element.getAttribute("id");
				String type = element.getAttribute("type");
				String parent = element.getAttribute("parent");
				String para = element.getAttribute("para");
				
				String className = PropertiesReader.value(type);
				if (className == null) {
					try {
						throw new ViewTypeNotExistException("类型(" + type + ")不存在!");
					} catch (ViewTypeNotExistException e) {
						e.printStackTrace();
						return;
					}
				}
				try {
					Class<?> klass = Class.forName(className);
					ParameterList parameterList = new ParaList(para);
					Class<?>[] types = parameterList.getTypeList();
					Object[] paras = parameterList.getValueList();
					
					Object object = Util.newInstance(klass, types, paras);
					
					viewFactory.addElement(id, new ViewElement(klass, object));
					dealMethod(element,new ViewElement(klass, object));
					if (parent == null || parent.length() == 0) {
						return;
					}
					Object parentView = viewFactory.getElement(parent);
					if (parentView instanceof IContainer 
							&& object instanceof JComponent) {
						IContainer container = (IContainer) parentView;
						container.addElement((JComponent) object);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.dealElementByTagName(element, "controller");
	}
	
	protected static void dealMethod(Element element,ViewElement view) {
		new XMLReader() {
			@Override
			public void dealElement(Element element, int index) {
				ParameterList parameterList;
				String name = element.getAttribute("name");
				String para = element.getAttribute("para");
				if(name.startsWith("=")) {
					name = PropertiesReader.value(name.substring(1));
				}
				if(para.startsWith("=")) {
					para = PropertiesReader.value(para.substring(1));
				}
				if(para.equalsIgnoreCase("null")) {
				}
				parameterList = new ParaList(para);
				Class<?>[] types = parameterList.getTypeList();
				Object[] paras = parameterList.getValueList(); 
				try {
					Method method = view.getType().getMethod(name, types);
					method.invoke(view.getObject(), paras);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.dealElementByTagName(element, "method");
	}

	private static Parameter dealConstance(String para) {
		return null;
	}
	
	//类类型参数的格式为#viewId;controllerId
	//不是controller的类参数用Class进行特殊处理;书写格式为:#类名;类参数
	//TODO
	private static Parameter dealViewElement(String para) {
		String [] paras = para.split(";");
        if(paras.length>2) {
        	for(int i = 2;i< paras.length;i++) {
        		paras[1] = paras[1]+","+paras[i];
        	}
        }
		Parameter parameter = new Parameter();
		if(!viewFactoryMap.containsKey(paras[0])) {
			try {
				if(paras[1].equalsIgnoreCase("null")) {
					Class<?> klass = Class.forName(paras[0]);
					parameter.setType(klass);
					Object obj = null;
					parameter.setValue(obj);
					return parameter;
				}else {
					Class<?> klass = Class.forName(paras[0]);
					ParameterList parameterList = new ParaList(paras[1]);
					Class<?> [] types = parameterList.getTypeList();
					Object[] objs = parameterList.getValueList();
					Object obj = Util.newInstance(klass, types, objs);
					parameter.setType(klass);
					parameter.setValue(obj);
					return parameter;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			ViewElement viewElement = viewFactoryMap.get(paras[0]).getElement(paras[1]);
			parameter.setType(viewElement.getType());
			parameter.setValue(viewElement.getObject());
			return parameter;
		}
		return parameter;
	}
	
	public static ViewFactory load(File xmlFile) {
		try {
			InputStream is = new FileInputStream(xmlFile);
			return load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ViewFactory load(String xmlPath) {
		return load(
				ViewFactoryBuilder.class
				.getResourceAsStream(xmlPath));
	}
	
	static class ParaList extends ParameterList {
		protected ParaList() {
		}

		protected ParaList(String paraStr) {
			super(paraStr);
		}

		@Override
		public Parameter getTypeAndValue(String para) {
			if (para.startsWith("=")) {
				return dealConstance(para.substring(1));
			}
			if (para.startsWith("#")) {
				return dealViewElement(para.substring(1));
			}
			try {
				throw new IllegalParameterException("不可识别的参数:"
						+ para);
			} catch (IllegalParameterException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
}
