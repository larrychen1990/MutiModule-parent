package com.alexgaoyh.MutiModule.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResourceExample;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;

public class SysmanResourceServiceTest {

	private ISysmanResourceService sysmanResourceService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        sysmanResourceService = (ISysmanResourceService) ctx.getBean( "sysmanResourceService" );
        
    }
	
	//@Test
	public void insert() {
		try {
			SysmanResource sysmanResource = new SysmanResource();
			sysmanResource.setDeleteflag(0);
			sysmanResource.setCreatetime(new Date());
			sysmanResource.setName("test");
			sysmanResource.setDescription("alexgaoyh");
			sysmanResourceService.insert(sysmanResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysmanResource sysmanResource = sysmanResourceService.selectByPrimaryKey(2);
		System.out.println(sysmanResource);
	}
	
	//@Test
	public void getPanigationByRowBounds() {
		
		SysmanResourceExample example = new SysmanResourceExample();
		example.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds = new MyRowBounds(3,10);
		example.setMyRowBounds(myRowBounds);
		
		sysmanResourceService.getPanigationByRowBounds(example);
		
	}
	
	@Test
	public void updateByPrimaryKeySelective() {
		SysmanResource sysmanResource = new SysmanResource();
		sysmanResource.setId(2);
		sysmanResource.setName("alexgaoyh1");
		
		sysmanResourceService.updateByPrimaryKeySelective(sysmanResource);
	}
	
	@Test
	public void selectResourceListBySysmanRoleId() {
		List<SysmanResource> sysmanResourceList = sysmanResourceService.selectResourceListBySysmanRoleId(1);
		for(SysmanResource sysmanResource : sysmanResourceList) {
			System.out.println("selectResourceListBySysmanRoleId:sysmanResource.getId() = " + sysmanResource.getId());
		}
	}
	
	@Test
	public void checkSysmanResourcePermission() {
		Boolean trueBoolean = sysmanResourceService.checkSysmanResourcePermission(1, 1);
		assertTrue(trueBoolean);
		
		Boolean falseBoolean = sysmanResourceService.checkSysmanResourcePermission(10, 10);
		assertFalse(falseBoolean);
	}
}
