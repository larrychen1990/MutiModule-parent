package com.alexgaoyh.MutiModule.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.captcha.CaptchaService;
import com.alexgaoyh.MutiModule.service.facade.IFacadeService;

public class FacadeServiceTest {

    private IFacadeService facadeService;

    //@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml", "module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        //获取module-captcha.xml 包含的 captchaService 的bean
        CaptchaService captchaService = (CaptchaService) ctx.getBean( "captchaService" );
        
        //获取module-service.xml 包含的 facadeService 的bean
        facadeService = (IFacadeService) ctx.getBean( "facadeService" );

    }

    //@Test
    public void testfacadeService() throws Exception  {
        // 1. Get captcha
        StringBuffer captchaKey = facadeService.generateCaptchaKey();
        System.out.println(captchaKey);
        
        facadeService.generateCaptchaImage( captchaKey );
    }
    

    //@After
    public void after() throws Exception  {
    	System.out.println("after method ---");
    }
}
