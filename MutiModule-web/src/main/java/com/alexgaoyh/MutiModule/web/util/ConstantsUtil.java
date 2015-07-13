package com.alexgaoyh.MutiModule.web.util;

/**
 * 静态常量定义
 * @author alexgaoyh
 *
 */
public class ConstantsUtil {

	/**
	 * redis 方面，key的过期时间，一分钟
	 */
	public static final int EXPIRE_TIME_1_MINUTE = 1 * 60;
	
	/**
	 * redis 方面。key的过期时间，三十分钟
	 */
	public static final int EXPIRE_TIME_30_MINUTE = 30 * 60;
	
	/**
	 * 类对象是否删除的状态标示
	 */
	public static final Integer DELETE_YES = 1;
	public static final Integer DELETE_NO = 0;
}
