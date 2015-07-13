package com.alexgaoyh.MutiModule.service.sysman;

import java.util.Date;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.MutiModule.common.utils.PaginationUtil;
import com.MutiModule.common.vo.mybatis.pagination.Page;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUserExample;

public class SysmanUserServiceTest {

	private ISysmanUserService sysmanUserService;
	
	@Before
    public void prepare() throws Exception  {
    	//可以加载多个配置文件
        String[] springConfigFiles = {"module-captcha.xml","mybatis-spring-config.xml","module-service-config.xml" };

        ApplicationContext ctx = new ClassPathXmlApplicationContext( springConfigFiles );

        sysmanUserService = (ISysmanUserService) ctx.getBean( "sysmanUserService" );
        
    }
	
	//@Test
	public void insert() {
		try {
			SysmanUser sysmanUser = new SysmanUser();
			sysmanUser.setName("test");
			sysmanUser.setPassword("aa");
			sysmanUser.setDeleteFlag(0);
			sysmanUser.setCreateTime(new Date());
			sysmanUserService.insert(sysmanUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void selectByPrimaryKey() {
		SysmanUser sysmanUser = sysmanUserService.selectByPrimaryKey(2);
		System.out.println(sysmanUser);
	}
	
	//@Test
	public void getPanigationByRowBounds() {
		
		SysmanUserExample exampleForCount = new SysmanUserExample();
		exampleForCount.setOrderByClause("id desc");
		
		SysmanUserExample exampleForList = new SysmanUserExample();
		exampleForList.setOrderByClause("id desc");
		
		Integer pageNumber = 1;
		Integer pageSize = 10;
		Page page = new Page(PaginationUtil.startValue(pageNumber, pageSize), pageSize);
		exampleForList.setPage(page);
		
		sysmanUserService.getPanigationByRowBounds(exampleForCount, exampleForList);
		
	}
	
	//@Test
	public void updateByPrimaryKeySelective() {
		SysmanUser sysmanUser = new SysmanUser();
		sysmanUser.setId(2);
		sysmanUser.setName("alexgaoyh1");
		
		sysmanUserService.updateByPrimaryKeySelective(sysmanUser);
	}
}
