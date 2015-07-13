package com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
			
			List<SysmanUserRoleRelKey> list = new ArrayList<SysmanUserRoleRelKey>();
			for(int i = 0; i < sysmanRoleIdsLength; i++) {
				SysmanUserRoleRelKey surr = new SysmanUserRoleRelKey();
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
