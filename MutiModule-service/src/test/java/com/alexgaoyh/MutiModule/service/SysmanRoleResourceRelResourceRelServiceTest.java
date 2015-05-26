package com.alexgaoyh.MutiModule.service;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleResourceRelService;

public class SysmanRoleResourceRelResourceRelServiceTest {

	private ISysmanRoleResourceRelService sysmanRoleResourceRelService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        sysmanRoleResourceRelService = (ISysmanRoleResourceRelService) ctx.getBean( "sysmanRoleResourceRelService" );
        
    }
	
}