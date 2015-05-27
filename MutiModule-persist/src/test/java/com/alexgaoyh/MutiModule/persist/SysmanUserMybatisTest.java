package com.alexgaoyh.MutiModule.persist;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
	public void testInsert() {
		
		try {
			SysmanUser sysmanUser = new SysmanUser();
			sysmanUser.setDeleteflag(0);
			sysmanUser.setName("alexgaoyh");
			sysmanUser.setCreatetime(new Date());
			sysmanUser.setPassword("alexgaoyh" + new Random(1000).nextInt());
			sysmanUserMapper.insert(sysmanUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSysmanUserExample5() {
		SysmanUserExample sysmanUserExample = new SysmanUserExample();
		sysmanUserExample.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds1 = new MyRowBounds(1,10);
		sysmanUserExample.setMyRowBounds(myRowBounds1);
		
		int count1 = sysmanUserMapper.countByExample(sysmanUserExample);
		List<SysmanUser> SysmanUserList1 = sysmanUserMapper.selectByExample(sysmanUserExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanUser SysmanUser : SysmanUserList1) {
			System.out.println("SysmanUser.getId() = " + SysmanUser.getId());
		}
		
	}
	
	@Test
	public void checkUserInfo() {
		SysmanUser su = sysmanUserMapper.selectUserByNameAndPasswd("alexgaoyh", "alexgaoyh1");
		assertNotNull(su);
		
		SysmanUser su1 = sysmanUserMapper.selectUserByNameAndPasswd("alexgaoyh1", "alexgaoyh");
		assertNull(su1);
	}
}
