package com.alexgaoyh.MutiModule.persist;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResourceExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResourceMapper;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;

public class SysmanResourceMybatisTest {

	private SysmanResourceMapper sysmanResourceMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanResourceMapper = (SysmanResourceMapper) ctx.getBean( "sysmanResourceMapper" );
        
    }
	
	@Test
	public void testInsert() {
		
		try {
			SysmanResource sysmanResource = new SysmanResource();
			sysmanResource.setDeleteflag(0);
			sysmanResource.setName("alexgaoyh");
			sysmanResource.setCreatetime(new Date());
			sysmanResource.setDescription("alexgaoyh");
			sysmanResourceMapper.insert(sysmanResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSysmanResourceExample() {
		SysmanResourceExample sysmanResourceExample = new SysmanResourceExample();
		sysmanResourceExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(1,10);
		sysmanResourceExample.setMyRowBounds(myRowBounds1);
		
		int count1 = sysmanResourceMapper.countByExample(sysmanResourceExample);
		List<SysmanResource> SysmanResourceList1 = sysmanResourceMapper.selectByExample(sysmanResourceExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanResource sysmanResource : SysmanResourceList1) {
			System.out.println("SysmanResource.getId() = " + sysmanResource.getId());
		}
		
	}
}
