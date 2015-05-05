package com.alexgaoyh.MutiModule.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserExample;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;

public class SysmanUserServiceTest {

	private ISysmanUserService sysmanUserService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        sysmanUserService = (ISysmanUserService) ctx.getBean( "sysmanUserService" );
        
    }
	
	@Test
	public void insert() {
		try {
			SysmanUser sysmanUser = new SysmanUser();
			sysmanUser.setName("test");
			sysmanUser.setPassword("aa");
			sysmanUser.setDeleteflag(0);
			sysmanUserService.insert(sysmanUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysmanUser sysmanUser = sysmanUserService.selectByPrimaryKey(1);
		System.out.println(sysmanUser);
	}
	
	//@Test
	public void getPanigationByRowBounds() {
		
		SysmanUserExample example = new SysmanUserExample();
		example.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds = new MyRowBounds(3,10);
		example.setMyRowBounds(myRowBounds);
		
		sysmanUserService.getPanigationByRowBounds(example);
		
	}
}
