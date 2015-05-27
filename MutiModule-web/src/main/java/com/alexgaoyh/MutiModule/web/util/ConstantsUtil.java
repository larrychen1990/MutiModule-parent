package com.alexgaoyh.MutiModule.web.util;

/**
 * 静态常量定义
 * @author alexgaoyh
 *
 */
public class ConstantsUtil {

	/**
	 * 后台登陆验证时的验证码信息
	 */
	public static final String ADMIN_CAPTCHA_CONSTANTS = "admin_captcha_constants";
	
	/**
	 * 用户登录后，将用户信息保存时对应的常量信息
	 */
	public static final String ADMIN_LOGIN_CONSTANTS = "admin_login_constants";
	
	/**
	 * redis 方面，key的过期时间，一分钟
	 */
	public static final int EXPIRE_TIME_1_MINUTE = 1 * 60;
	
	/**
	 * redis 方面。key的过期时间，三十分钟
	 */
	public static final int EXPIRE_TIME_30_MINUTE = 30 * 60;
}
