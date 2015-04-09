package com.alexgaoyh.MutiModule.service;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.captcha.CaptchaService;

public class FacadeServiceTest {

    private FacadeService facadeService;

    @Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml", "module-service.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        //获取module-captcha.xml 包含的 captchaService 的bean
        CaptchaService captchaService = (CaptchaService) ctx.getBean( "captchaService" );
        System.out.println(captchaService);
        
        //获取module-service.xml 包含的 facadeService 的bean
        facadeService = (FacadeService) ctx.getBean( "facadeService" );

    }

    @Test
    public void testfacadeService() throws Exception  {
        // 1. Get captcha
        StringBuffer captchaKey = facadeService.generateCaptchaKey();
        System.out.println(captchaKey);
        
        facadeService.generateCaptchaImage( captchaKey );
    }
    

    @After
    public void after() throws Exception  {
    	System.out.println("after method ---");
    }
}
