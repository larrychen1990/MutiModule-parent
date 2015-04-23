package com.alexgaoyh.MutiModule.persist;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;

public class DemoMyBatisTest {
	
	private  DemoMapper demoMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        demoMapper = (DemoMapper) ctx.getBean( "demoMapper" );
        
    }

	//@Test
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
	
	//@Test
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
	
	//@Test
	public void testSQL_countByExample_selectByExample() {
		int count = demoMapper.countByExample(null);
		List<Demo> demoList = demoMapper.selectByExample(null);
		
		System.out.println("count = " + count);
		for(Demo demo : demoList) {
			System.out.println("demo.getId() = " + demo.getId());
		}
	}
	
	//@Test
	public void testNullDemoExample() {
		DemoExample demoExample = new DemoExample();
		
		int count = demoMapper.countByExample(demoExample);
		List<Demo> demoList = demoMapper.selectByExample(demoExample);
		
		System.out.println("count = " + count);
		for(Demo demo : demoList) {
			System.out.println("demo.getId() = " + demo.getId());
		}
	}
	
	//@Test
	public void testDemoExample1() {
		DemoExample demoExample = new DemoExample();
		demoExample.setOrderByClause("id desc");
		
		int count = demoMapper.countByExample(demoExample);
		List<Demo> demoList = demoMapper.selectByExample(demoExample);
		
		System.out.println("count = " + count);
		for(Demo demo : demoList) {
			System.out.println("demo.getId() = " + demo.getId());
		}
	}
	
	@Test
	public void testDemoExample2() {
		DemoExample demoExample = new DemoExample();
		demoExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds0 = new MyRowBounds(1,10);
		demoExample.setMyRowBounds(myRowBounds0);
		
		int count0 = demoMapper.countByExample(demoExample);
		List<Demo> demoList0 = demoMapper.selectByExample(demoExample);
		
		System.out.println("count0 = " + count0);
		for(Demo demo : demoList0) {
			System.out.println("demo.getId() = " + demo.getId());
		}
		
	}
	
	@Test
	public void testDemoExample3() {
		DemoExample demoExample = new DemoExample();
		demoExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(2,10);
		demoExample.setMyRowBounds(myRowBounds1);
		
		int count1 = demoMapper.countByExample(demoExample);
		List<Demo> demoList1 = demoMapper.selectByExample(demoExample);
		
		System.out.println("count1 = " + count1);
		for(Demo demo : demoList1) {
			System.out.println("demo.getId() = " + demo.getId());
		}
		
	}
	
	@Test
	public void testDemoExample4() {
		DemoExample demoExample = new DemoExample();
		demoExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(3,10);
		demoExample.setMyRowBounds(myRowBounds1);
		
		int count1 = demoMapper.countByExample(demoExample);
		List<Demo> demoList1 = demoMapper.selectByExample(demoExample);
		
		System.out.println("count1 = " + count1);
		for(Demo demo : demoList1) {
			System.out.println("demo.getId() = " + demo.getId());
		}
		
	}
	
	@Test
	public void testDemoExample5() {
		DemoExample demoExample = new DemoExample();
		demoExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(4,10);
		demoExample.setMyRowBounds(myRowBounds1);
		
		int count1 = demoMapper.countByExample(demoExample);
		List<Demo> demoList1 = demoMapper.selectByExample(demoExample);
		
		System.out.println("count1 = " + count1);
		for(Demo demo : demoList1) {
			System.out.println("demo.getId() = " + demo.getId());
		}
		
	}
	
}
