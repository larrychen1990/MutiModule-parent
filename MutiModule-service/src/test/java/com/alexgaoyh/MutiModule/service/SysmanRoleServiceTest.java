package com.alexgaoyh.MutiModule.service;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleExample;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleService;

public class SysmanRoleServiceTest {

	private ISysmanRoleService sysmanRoleService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        sysmanRoleService = (ISysmanRoleService) ctx.getBean( "sysmanRoleService" );
        
    }
	
	//@Test
	public void insert() {
		try {
			SysmanRole sysmanRole = new SysmanRole();
			sysmanRole.setDeleteflag(0);
			sysmanRole.setCreatetime(new Date());
			sysmanRole.setName("test");
			sysmanRole.setDescription("alexgaoyh");
			sysmanRoleService.insert(sysmanRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void selectByPrimaryKey() {
		SysmanRole sysmanRole = sysmanRoleService.selectByPrimaryKey(2);
		System.out.println(sysmanRole);
	}
	
	//@Test
	public void getPanigationByRowBounds() {
		
		SysmanRoleExample example = new SysmanRoleExample();
		example.setOrderByClause("id desc");
		
		sysmanRoleService.getPanigationByRowBounds(example);
		
	}
	
	//@Test
	public void updateByPrimaryKeySelective() {
		SysmanRole sysmanRole = new SysmanRole();
		sysmanRole.setId(2);
		sysmanRole.setName("alexgaoyh1");
		
		sysmanRoleService.updateByPrimaryKeySelective(sysmanRole);
	}
	
	//@Test
	public void selectRoleListBySysmanUserId() {
		List<SysmanRole> sysmanRoleList = sysmanRoleService.selectRoleListBySysmanUserId(1);
		for(SysmanRole sysmanRole : sysmanRoleList) {
			System.out.println("selectRoleListBySysmanUserId:sysmanRole.getId() = " + sysmanRole.getId());
		}
	}
}
