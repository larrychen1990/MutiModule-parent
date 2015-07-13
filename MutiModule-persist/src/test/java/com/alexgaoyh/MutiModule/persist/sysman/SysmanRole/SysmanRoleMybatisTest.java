package com.alexgaoyh.MutiModule.persist.sysman.SysmanRole;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
			sysmanRole.setDeleteFlag(0);
			sysmanRole.setName("alexgaoyh");
			sysmanRole.setCreateTime(new Date());
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
		
		int count1 = sysmanRoleMapper.countByExample(sysmanRoleExample);
		List<SysmanRole> SysmanRoleList1 = sysmanRoleMapper.selectByExample(sysmanRoleExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanRole sysmanRole : SysmanRoleList1) {
			System.out.println("SysmanRole.getId() = " + sysmanRole.getId());
		}
		
	}
}
