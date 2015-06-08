package com.alexgaoyh.MutiModule.codeGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CodeGeneratorUtil {

	public static void main(String[] args) {
		demo();
	}

	public static void demo() {
		Map<String, Object> root = new HashMap<String, Object>();
		//子文件的包名
		root.put("packageName", "demo");// 子包包名，整个包的路径为   com.alexgaoyh.MutiModule+（模块:persist/service）
		//实体类名称
		root.put("className", "Demo");// 类名称
		//实体类名称首字母小写，驼峰式
		root.put("smallClassName", "demo");// 类名称的首字母小写
		
		String workDir = (String) System.getProperties().get("user.dir");
		try {
			service(workDir, root);
			serviceImpl(workDir, root);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void service(String workDir, Map<String, Object> input)
			throws Exception {
		String fileName = workDir + "/src/main/java/"
				+ input.get("packageName").toString().replaceAll("\\.", "/")
				+ "/service/" + "I" + input.get("className").toString()
				+ "Service.java";
		File myFile = new File(fileName);
		myFile.getParentFile().mkdirs();
		myFile.createNewFile();
		buildFile("templete/service.ftl", fileName, input);
	}

	public static void serviceImpl(String workDir, Map<String, Object> input)
			throws Exception {
		String fileName = workDir + "/src/main/java/"
				+ input.get("packageName").toString().replaceAll("\\.", "/")
				+ "/service/impl/" + input.get("className").toString()
				+ "ServiceImpl.java";
		File myFile = new File(fileName);
		myFile.getParentFile().mkdirs();
		myFile.createNewFile();
		buildFile("templete/serviceImpl.ftl", fileName, input);
	}
	
	/**
	 * 生成文件
	 * 
	 * @param templateName
	 *            模板文件
	 * @param fileName
	 *            生成文件
	 * @param root
	 *            参数
	 */
	private static void buildFile(String templateName, String fileName, Map root) {
		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(CodeGeneratorUtil.class, "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		Template template;
		try {
			template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			File htmlFile = new File(fileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			template.process(root, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
