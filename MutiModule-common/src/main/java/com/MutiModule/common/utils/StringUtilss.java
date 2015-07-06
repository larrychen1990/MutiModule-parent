package com.MutiModule.common.utils;

public class StringUtilss {

	/**
	 * 获取文件后缀
	 * @param src	文件路径/名称	文件路径   C:\Users\Public\Pictures\Sample Pictures\test.jpg
	 * @return	如果文件后缀		jpg
	 */
	public static String getFileExt(String src) {
		String[] _splitArrays = src.split(".");
		if(_splitArrays.length != 2) {
			return null;
		} else {
			return _splitArrays[1];
		}
	}
	
	/**
	 * 获取文件名称，不带文件后缀部分
	 * @param src	文件路径   C:\Users\Public\Pictures\Sample Pictures\test.jpg
	 * @return	文件名称 不带文件后缀   	test
	 */
	public static String getFileName(String src) {
		
		String filename= src .substring(src .lastIndexOf("\\\\") + 1, src .length());//获取到文件名
		
		String[] _splitArrays = filename.split(".");
		if(_splitArrays.length != 2) {
			return null;
		} else {
			return _splitArrays[0];
		}
	}
}
