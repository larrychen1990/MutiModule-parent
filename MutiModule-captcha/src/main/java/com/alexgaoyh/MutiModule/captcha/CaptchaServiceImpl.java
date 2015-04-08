package com.alexgaoyh.MutiModule.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.InitializingBean;

public class CaptchaServiceImpl implements CaptchaService, InitializingBean {

	/**
	 * 存放驗證碼key
	 */
    private StringBuffer captchaKey;

    
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StringBuffer generateCaptchaKey() throws CaptchaException {
		captchaKey = RandomGenerator.getRandomString();
		return captchaKey;
	}

	@Override
	public byte[] generateCaptchaImage(StringBuffer captchaKey) throws CaptchaException {
		
		CaptchaUtil tool = new CaptchaUtil();

        BufferedImage image = tool.genRandomCodeImage(captchaKey);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try
        {
            ImageIO.write( image, "jpg", out );
        }
        catch ( IOException e )
        {
            throw new CaptchaException( "Failed to write captcha stream!", e );
        }

        return out.toByteArray();
	}

	@Override
	public boolean validateCaptcha(StringBuffer captchaKey, StringBuffer inputValue) throws CaptchaException {
		if ( captchaKey.toString().equals( inputValue.toString() ) ) {
            return true;
        }  else {
            return false;
        }
	}

	@Override
	public StringBuffer getCaptchaKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCaptchaKey(StringBuffer captchaKey) {
		// TODO Auto-generated method stub
		
	}


}
