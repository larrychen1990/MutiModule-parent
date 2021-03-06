package com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRel;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SysmanRoleResourceRelMybatisTest {
	
	private SysmanRoleResourceRelMapper sysmanRoleResourceRelMapper;

	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanRoleResourceRelMapper = (SysmanRoleResourceRelMapper) ctx.getBean( "sysmanRoleResourceRelMapper" );
        
    }
	
	//@Test
	public void checkRoleIdResourceIdExisted() {
		Boolean falseBoolean = sysmanRoleResourceRelMapper.checkRoleIdResourceIdExisted(100, 100);
		assertFalse(falseBoolean);
		
	}
}
