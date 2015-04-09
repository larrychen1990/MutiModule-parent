package com.alexgaoyh.MutiModule.service;

/**
 * 定义形如Facade设计模式的 封装接口
 * @author alexgaoyh
 *
 */
public interface FacadeService {
	
	/**
	 * 产生随机key
	 * @return
	 * @throws FacadeServiceException
	 */
	StringBuffer generateCaptchaKey() throws FacadeServiceException;

	/**
	 * 分局随机key ,生成验证码
	 * @param captchaKey
	 * @return
	 * @throws FacadeServiceException
	 */
    byte[] generateCaptchaImage( StringBuffer captchaKey ) throws FacadeServiceException;

}
