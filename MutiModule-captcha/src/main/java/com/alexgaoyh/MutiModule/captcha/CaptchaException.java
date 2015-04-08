package com.alexgaoyh.MutiModule.captcha;

/**
 * 验证码自定义异常
 * 
 * @author Administrator
 * 
 */
public class CaptchaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8581188699677371241L;

	public CaptchaException() {

		super();

	}

	public CaptchaException(String message, Throwable cause) {

		super(message, cause);

	}

	public CaptchaException(String message) {

		super(message);

	}

	public CaptchaException(Throwable cause) {

		super(cause);

	}

}
