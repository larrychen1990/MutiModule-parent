package com.MutiModule.common.utils;

import java.io.File;

public class StringUtilss {

	/**
	 * 获取文件后缀
	 * @param src	文件路径/名称	文件路径   C:\Users\Public\Pictures\Sample Pictures\test.jpg
	 * @return	如果文件后缀		jpg
	 */
	public static String getFileExt(String src) {
		
		String filename= src .substring(src .lastIndexOf(File.separator) + 1, src .length());//获取到文件名
		
		return filename.substring(filename.lastIndexOf(".") + 1);
	}
	
	/**
	 * 获取文件名称，不带文件后缀部分
	 * @param src	文件路径   C:\Users\Public\Pictures\Sample Pictures\test.jpg
	 * @return	文件名称 不带文件后缀   	test
	 */
	public static String getFileName(String src) {
		
		String filename= src .substring(src .lastIndexOf(File.separator) + 1, src .length());//获取到文件名
		
		return filename.substring(0, filename.lastIndexOf("."));
	}
}
