package com.alexgaoyh.MutiModule.persist;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
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
			Demo demo = new Demo();
			demo.setDeleteflagstate(0);
			demo.setName("test");
			demoMapper.insert(demo);
			//返回受影响的条数 keyProperty的值为demoMapper.insertDemo(demo)的返回值
			//System.out.println("keyProperty=" + keyProperty);
			//返回生成的主键id
			//System.out.println("demo.getId()=" + demo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectById() {
		
		try {
			Demo demo = demoMapper.selectByPrimaryKey(100);
			if(demo != null) {
				System.out.println(demo.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
