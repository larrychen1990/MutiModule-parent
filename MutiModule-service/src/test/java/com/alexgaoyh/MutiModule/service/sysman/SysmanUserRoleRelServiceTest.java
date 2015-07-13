package com.alexgaoyh.MutiModule.service.sysman;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SysmanUserRoleRelServiceTest {

	private ISysmanUserRoleRelService sysmanUserRoleRelService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        sysmanUserRoleRelService = (ISysmanUserRoleRelService) ctx.getBean( "sysmanUserRoleRelService" );
        
    }
	
	//@Test
	public void removeOldRelAndSaveNewRel() {
		sysmanUserRoleRelService.removeOldRelAndSaveNewRel(2, "2,3");
	}
	
}
