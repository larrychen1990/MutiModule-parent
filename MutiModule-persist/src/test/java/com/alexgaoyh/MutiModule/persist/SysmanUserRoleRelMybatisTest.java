package com.alexgaoyh.MutiModule.persist;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRelMapper;

public class SysmanUserRoleRelMybatisTest {

	private SysmanUserRoleRelMapper sysmanUserRoleRelMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanUserRoleRelMapper = (SysmanUserRoleRelMapper) ctx.getBean( "sysmanUserRoleRelMapper" );
        
    }
	
	//@Test
	public void insertbatch() {
		String sysmanRoleIds = "2,3";
		try {
			String[] sysmanRoleIdsArray = sysmanRoleIds.split(",");
			int sysmanRoleIdsLength = sysmanRoleIdsArray.length;
			
			List<SysmanUserRoleRel> list = new ArrayList<SysmanUserRoleRel>();
			for(int i = 0; i < sysmanRoleIdsLength; i++) {
				SysmanUserRoleRel surr = new SysmanUserRoleRel();
				surr.setSysmanUserId(2);
				surr.setSysmanRoleId(Integer.parseInt(sysmanRoleIdsArray[i]));
				list.add(surr);
			}
			System.out.println(list.size());
			sysmanUserRoleRelMapper.insertbatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
