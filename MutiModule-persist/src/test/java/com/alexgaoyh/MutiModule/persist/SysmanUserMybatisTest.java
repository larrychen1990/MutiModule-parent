package com.alexgaoyh.MutiModule.persist;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserMapper;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;

public class SysmanUserMybatisTest {

	private SysmanUserMapper sysmanUserMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanUserMapper = (SysmanUserMapper) ctx.getBean( "sysmanUserMapper" );
        
    }
	
	@Test
	public void testSysmanUserExample5() {
		SysmanUserExample sysmanUserExample = new SysmanUserExample();
		sysmanUserExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(4,10);
		sysmanUserExample.setMyRowBounds(myRowBounds1);
		
		int count1 = sysmanUserMapper.countByExample(sysmanUserExample);
		List<SysmanUser> SysmanUserList1 = sysmanUserMapper.selectByExample(sysmanUserExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanUser SysmanUser : SysmanUserList1) {
			System.out.println("SysmanUser.getId() = " + SysmanUser.getId());
		}
		
	}
}
