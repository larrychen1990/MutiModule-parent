package com.alexgaoyh.MutiModule.web.util;

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
}
