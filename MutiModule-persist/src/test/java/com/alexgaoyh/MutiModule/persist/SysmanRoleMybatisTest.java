package com.alexgaoyh.MutiModule.persist;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleMapper;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;

public class SysmanRoleMybatisTest {

	private SysmanRoleMapper sysmanRoleMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanRoleMapper = (SysmanRoleMapper) ctx.getBean( "sysmanRoleMapper" );
        
    }
	
	//@Test
	public void testInsert() {
		
		try {
			SysmanRole sysmanRole = new SysmanRole();
			sysmanRole.setDeleteflag(0);
			sysmanRole.setName("alexgaoyh");
			sysmanRole.setCreatetime(new Date());
			sysmanRole.setDescription("alexgaoyh");
			sysmanRoleMapper.insert(sysmanRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testSysmanRoleExample() {
		SysmanRoleExample sysmanRoleExample = new SysmanRoleExample();
		sysmanRoleExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(1,10);
		sysmanRoleExample.setMyRowBounds(myRowBounds1);
		
		int count1 = sysmanRoleMapper.countByExample(sysmanRoleExample);
		List<SysmanRole> SysmanRoleList1 = sysmanRoleMapper.selectByExample(sysmanRoleExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanRole sysmanRole : SysmanRoleList1) {
			System.out.println("SysmanRole.getId() = " + sysmanRole.getId());
		}
		
	}
}
