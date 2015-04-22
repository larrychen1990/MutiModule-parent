package com.alexgaoyh.MutiModule.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.service.demo.IDemoService;

public class DemoServiceTest {

	private IDemoService demoService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        demoService = (IDemoService) ctx.getBean( "demoService" );
        System.out.println(demoService);
        
    }
	
	@Test
	public void insertDemo() {
		try {
			Demo demo = new Demo();
			demo.setName("test");
			demo.setDeleteflagstate(0);
			demoService.insert(demo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加事务支持，测试方法中调用 demoService.insertDemoForTransaction 
	 * 此方法内部调用了两个insert语句，其中一个表结构存在，另外一个不存在，抛出异常信息，之后事务回滚，将前面一个插入语句的信息回滚，数据库中看不到相对应的数据
	 */
//	@Test
//	public void insertDemoForTransaction() {
//		try {
//			DemoEntity demo = new DemoEntity();
//			demo.setName("insertDemoForTransaction");
//			demoService.insertDemoForTransaction(demo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
