package com.alexgaoyh.MutiModule.persist;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;

public class DemoMyBatisTest {
	
	private  DemoMapper demoMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        demoMapper = (DemoMapper) ctx.getBean( "demoMapper" );
        
    }

	@Test
	public void testInsert() {
		
		try {
			DemoEntity demo = new DemoEntity();
			demo.setName("test");
			demoMapper.insertDemo(demo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectById() {
		
		try {
			DemoEntity demo = demoMapper.selectDemoById(2);
			if(demo != null) {
				System.out.println(demo.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
