package com.MutiModule.common.xml.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.MutiModule.common.utils.XMLUtilss;

/**
 * 生成xml的方法
 * 注意两个单元测试的方法中传入的第四个参数的不同
 * @author lenovo
 *
 */
public class GenerateXMLTest {
	
	/**
	 * 根据word生成相关的md5
	 * XMLUtillss.generateXML 的4个参数分别代表
	 * 请求报文的格式化报文格式	
	 * 请求报文的请求内容（map集合的key与xml文件的节点相匹配）
	 * 请求报文的xml节点相对应的实体类的决定路径
	 * 如果请求报文的xml文件中，各个子节点并不属于root节点的子节点，则需要传入中间相隔的节点部分
	 * @return
	 */
	@Test
	public void generateInputXML1() {
		
		Map<String, Object> inputMap = new HashMap<String, Object>();
		
		inputMap.put("PrimaryNo", "123456");
		
		String inputXML2 = XMLUtilss.generateXML("test/inputXML1.XML", inputMap, "com.MutiModule.common.xml.entity.InputXML1", "Policy/");
		
		System.out.println(inputXML2);
	}
	
	/**
	 * 根据word生成相关的md5
	 * @return
	 */
	//@Test
	public void generateInputXML2() {
		
		Map<String, Object> inputMap = new HashMap<String, Object>();
		
		inputMap.put("String", "123456");
		inputMap.put("md5key", "123456");
		
		String inputXML2 = XMLUtilss.generateXML("test/inputXML2.XML", inputMap, "com.MutiModule.common.xml.entity.InputXML2", "");
		
		System.out.println(inputXML2);
	}

	
	
}
