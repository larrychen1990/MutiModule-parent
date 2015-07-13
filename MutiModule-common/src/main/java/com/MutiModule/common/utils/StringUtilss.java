package com.MutiModule.common.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilss {
	
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
   
    public static boolean isNotEmpty(String str) {
        return !StringUtilss.isEmpty(str);
    }
   
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
   
    public static boolean isNotBlank(String str) {
        return !StringUtilss.isBlank(str);
    }
    
    /*** 
     * 判断 String 是否int 
     *  
     * @param input 
     * @return 
     */  
    public static boolean isInteger(String input){
    	Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);
        return mer.find();  
    }
    
    /**
     * 数字型String字符串转换成int型数组
     * @param str string类型的数组
     * @return
     */
    public static Integer[] stringToIntegerArray(String[] str) {
    	Integer array[] = new Integer[str.length];  
    	for(int i=0;i<str.length;i++){  
    	    array[i]=Integer.parseInt(str[i]);
    	}
    	return array;
    }

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
