package com.alexgaoyh.MutiModule.web.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密类
 * @author alexgaoyh
 *
 */
public class MD5Util {

	/* 
     * 2.使用开发的jar直接应用 
     *  使用外部的jar包中的类：import org.apache.commons.codec.digest.DigestUtils; 
     *  对上面内容的一个封装使用方便 
     */  
    public static String encrypByMd5Jar(String context) {  
        String md5Str = DigestUtils.md5Hex(context);
        System.out.println("32result: " + md5Str); 
        return md5Str;
    }
    
    public static void main(String[] args) {  
        MD5Util.encrypByMd5Jar("alexgaoyh1");  
    }  
}
