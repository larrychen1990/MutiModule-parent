package com.MutiModule.common.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtilss {

	/**
	 * 生成xml 数据格式
	 * @param sourceXML 请求报文xml的文件
	 * @param inputMap	请求报文的参数，内部map的key值与xml的节点匹配
	 * @param classPath	请求类的决定路径
	 * @param extraXML	额外的匹配xml节点路径部分
	 * @return	整合的xml字符串
	 */
	public static String generateXML(String sourceXML, Map<String, Object> inputMap, String classPath, String extraXML) {

		String resultStr = null;
		
		try {
			Class<?> classDemo = Class.forName(classPath);
			
			Field[] fields = classDemo.getDeclaredFields();
			
			try {
				InputStream inputStream = XMLUtilss.class.getClassLoader().getResourceAsStream(sourceXML);

				Document doc = new SAXReader().read(inputStream);

				Element root = XmlParserUtilss.getRootNode(doc);
				
				for (int i = 0; i < fields.length; i++) {
					
					String fieldName = fields[i].getName();
					
					Element ele = XmlParserUtilss.parseSqlXML(root, extraXML + fieldName);
					
					if(ele != null) {
						Object mapValue = inputMap.get(fieldName);
						if( mapValue != null) {
							ele.setText(mapValue.toString());
						}
					}
							
				}
				
				resultStr = doc.asXML();
				
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return resultStr;
		
	}
}
