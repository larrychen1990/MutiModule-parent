package com.alexgaoyh.MutiModule.service.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alexgaoyh.MutiModule.captcha.CaptchaException;
import com.alexgaoyh.MutiModule.captcha.CaptchaService;
import com.alexgaoyh.MutiModule.service.facade.IFacadeService;
import com.alexgaoyh.MutiModule.service.facade.exception.FacadeServiceException;

/**
 * 实现类
 * @author alexgaoyh
 *
 */
@Service(value="facadeService")
public class FacadeServiceImpl implements IFacadeService {

	@Resource(name="captchaService")
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
