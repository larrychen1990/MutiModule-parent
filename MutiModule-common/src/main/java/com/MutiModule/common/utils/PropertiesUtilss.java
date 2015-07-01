package com.MutiModule.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 通过静态内部类实现 单例模型 满足线程安全
 * 
 * @author alexgaoyh
 * 
 */
public class PropertiesUtilss {

	/**
	 * 從fileUrl文件中，讀取keyName對應的value值
	 * 
	 * @param fileUrl
	 *            配置文件文件路徑
	 * @param keyName
	 *            配置文件中讀取的key名稱
	 * @return
	 */
	public static Object getProperties(String fileUrl, String keyName) {
		Properties prop = new Properties();
		InputStream in = PropertiesUtilss.class.getClassLoader().getResourceAsStream(fileUrl);
		try {
			if(in != null) {
				prop.load(in);
				return prop.getProperty(keyName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
