package com.alexgaoyh.MutiModule.captcha;

import java.util.List;

public interface CaptchaService
{
	StringBuffer generateCaptchaKey() throws CaptchaException;

    byte[] generateCaptchaImage( StringBuffer captchaKey ) throws CaptchaException;

    boolean validateCaptcha( StringBuffer captchaKey, StringBuffer inputValue ) throws CaptchaException;

    StringBuffer getCaptchaKey();

    void setCaptchaKey( StringBuffer captchaKey );
}
