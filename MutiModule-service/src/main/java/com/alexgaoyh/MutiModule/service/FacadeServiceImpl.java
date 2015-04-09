package com.alexgaoyh.MutiModule.service;

import com.alexgaoyh.MutiModule.captcha.CaptchaException;
import com.alexgaoyh.MutiModule.captcha.CaptchaService;

/**
 * 实现类
 * @author alexgaoyh
 *
 */
public class FacadeServiceImpl implements FacadeService {

    private CaptchaService captchaService;
    
    //------------------get set方法 begin
    public CaptchaService getCaptchaService() {
    	return captchaService;
    }
    
    public void setCaptchaService(CaptchaService captchaService) {
    	this.captchaService = captchaService;
    }
	//------------------get set方法 end

	public byte[] generateCaptchaImage( StringBuffer captchaKey ) throws FacadeServiceException {
        try {
            return captchaService.generateCaptchaImage( captchaKey );
        }
        catch (CaptchaException e ) {
            throw new FacadeServiceException( "Unable to generate Captcha Image.", e );
        }
    }

    public StringBuffer generateCaptchaKey() throws FacadeServiceException {
        try  {
            return captchaService.generateCaptchaKey();
        }
        catch ( CaptchaException e ) {
            throw new FacadeServiceException( "Unable to generate Captcha key.", e );
        }
    }

}
