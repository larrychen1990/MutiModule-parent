package com.alexgaoyh.MutiModule.persist;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.MutiModule.common.utils.PaginationUtil;
import com.MutiModule.common.vo.mybatis.pagination.Page;
import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;

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
			demo.setDeleteFlag(0);
			demo.setCreateTime(new Date());
			demo.setName("test");
			demoMapper.insert(demo);
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
	
	@Test
	public void testNullDemoExample() {
		DemoExample demoExample = new DemoExample();
		
		int count = demoMapper.countByExample(demoExample);
		List<Demo> demoList = demoMapper.selectByExample(demoExample);
		
		System.out.println("count = " + count);
		for(Demo demo : demoList) {
			System.out.println("demo.getId() = " + demo.getId());
		}
	}
	
	@Test
	public void testDemoExample1() {
		DemoExample demoExampleForCount = new DemoExample();
		demoExampleForCount.setOrderByClause("id asc");
		
		DemoExample demoExampleForList = new DemoExample();
		demoExampleForList.setOrderByClause("id asc");
		
		int pageNumber = 1;
		int pageSize = 1;
		Page page = new Page(PaginationUtil.startValue(pageNumber, pageSize), pageSize);
		demoExampleForList.setPage(page);
		
		int count0 = demoMapper.countByExample(demoExampleForCount);
		List<Demo> demoList0 = demoMapper.selectByExample(demoExampleForList);
		
		System.out.println("testDemoExample2 count0 = " + count0);
		for(Demo demo : demoList0) {
			System.out.println("testDemoExample2 demo.getId() = " + demo.getId());
		}
	}
	
	@Test
	public void deleteLogicByIds() {
		Integer[] ids = {4,5,6,7,8};
		Integer deleteCount = demoMapper.deleteLogicByIds(1, ids);
		System.out.println("deleteCount = " + deleteCount);
	}
}
