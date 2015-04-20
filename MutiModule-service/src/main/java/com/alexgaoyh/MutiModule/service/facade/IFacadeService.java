package com.alexgaoyh.MutiModule.service.facade;

import com.alexgaoyh.MutiModule.service.facade.exception.FacadeServiceException;

/**
 * 定义形如Facade设计模式的 封装接口
 * @author alexgaoyh
 *
 */
public interface IFacadeService {
	
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
