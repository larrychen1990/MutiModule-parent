package com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SysmanUserMybatisTest {

	private SysmanUserMapper sysmanUserMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanUserMapper = (SysmanUserMapper) ctx.getBean( "sysmanUserMapper" );
        
    }
	
	//@Test
	public void testInsert() {
		
		try {
			SysmanUser sysmanUser = new SysmanUser();
			sysmanUser.setDeleteFlag(0);
			sysmanUser.setName("alexgaoyh");
			sysmanUser.setCreateTime(new Date());
			sysmanUser.setPassword("alexgaoyh" + new Random(1000).nextInt());
			sysmanUserMapper.insert(sysmanUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testSysmanUserExample5() {
		SysmanUserExample sysmanUserExample = new SysmanUserExample();
		sysmanUserExample.setOrderByClause("id desc");
		
		int count1 = sysmanUserMapper.countByExample(sysmanUserExample);
		List<SysmanUser> SysmanUserList1 = sysmanUserMapper.selectByExample(sysmanUserExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanUser SysmanUser : SysmanUserList1) {
			System.out.println("SysmanUser.getId() = " + SysmanUser.getId());
		}
		
	}
	
	//@Test
	public void checkUserInfo() {
		SysmanUser su = sysmanUserMapper.selectUserByNameAndPasswd("alexgaoyh", "17ec80aebe6c2bedf0e36b75eabe9519");
		assertNotNull(su);
		
		SysmanUser su1 = sysmanUserMapper.selectUserByNameAndPasswd("alexgaoyh1", "alexgaoyh");
		assertNull(su1);
	}
	
	//@Test
	public void deleteLogicByIds() {
		Integer[] ids = {4,5,6,7,8};
		int resultUpdateConut = sysmanUserMapper.deleteLogicByIds(1, ids);
		System.out.println("resultUpdateConut = " + resultUpdateConut);
	}
}
