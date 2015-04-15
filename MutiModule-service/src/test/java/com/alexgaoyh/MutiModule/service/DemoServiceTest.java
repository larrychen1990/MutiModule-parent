package com.alexgaoyh.MutiModule.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;
import com.alexgaoyh.MutiModule.service.demo.IDemoService;

public class DemoServiceTest {

	private IDemoService demoService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"mybatis-spring-config.xml","module-service-demo.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        demoService = (IDemoService) ctx.getBean( "demoService" );
        System.out.println(demoService);
        
    }
	
	@Test
	public void insertDemo() {
		try {
			DemoEntity demo = new DemoEntity();
			demo.setName("test");
			demoService.insertDemo(demo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
