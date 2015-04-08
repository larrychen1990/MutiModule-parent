package com.alexgaoyh.MutiModule.captcha;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AccountCaptchaServiceTest {
	
    private CaptchaService service;

    @Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "account-captcha.xml" );
        
        service = (CaptchaService) ctx.getBean( "accountCaptchaService" );
        
    }

    @Test
    public void testGenerateCaptcha() throws Exception {
    	
    	StringBuffer captchaKey = service.generateCaptchaKey();
        assertNotNull( captchaKey );

        byte[] captchaImage = service.generateCaptchaImage( captchaKey );
        assertTrue( captchaImage.length > 0 );

        File image = new File( "target/" + captchaKey + ".jpg" );
        OutputStream output = null;
        try {
            output = new FileOutputStream( image );
            output.write( captchaImage );
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
        assertTrue( image.exists() && image.length() > 0 );
    }

    @Test
    public void testValidateCaptchaCorrect() throws Exception {
    	

        StringBuffer captchaKey = new StringBuffer("1234");
        service.generateCaptchaImage( captchaKey );
        assertTrue( service.validateCaptcha( captchaKey, new StringBuffer("1234") ) );

    }

    @Test
    public void testValidateCaptchaIncorrect() throws Exception {
    	

    	StringBuffer captchaKey = new StringBuffer("1234");
        service.generateCaptchaImage( captchaKey );
        assertFalse( service.validateCaptcha( captchaKey, new StringBuffer("6789") ) );
        
    }
    
}
