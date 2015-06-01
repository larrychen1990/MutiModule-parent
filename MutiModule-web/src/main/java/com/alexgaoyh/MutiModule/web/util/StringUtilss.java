package com.alexgaoyh.MutiModule.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义的stringUtil类
 * @author alexgaoyh
 *
 */
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
}
