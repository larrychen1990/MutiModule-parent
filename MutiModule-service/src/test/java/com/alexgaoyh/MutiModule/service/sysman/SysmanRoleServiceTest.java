package com.alexgaoyh.MutiModule.service.sysman;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.MutiModule.common.utils.PaginationUtil;
import com.MutiModule.common.vo.mybatis.pagination.Page;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRoleExample;

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
			sysmanRole.setDeleteFlag(0);
			sysmanRole.setCreateTime(new Date());
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
		
		SysmanRoleExample exampleForCount = new SysmanRoleExample();
		exampleForCount.setOrderByClause("id desc");
		
		
		SysmanRoleExample exampleForList = new SysmanRoleExample();
		Integer pageNumber = 1;
		Integer pageSize = 10;
		Page page = new Page(PaginationUtil.startValue(pageNumber, pageSize), pageSize);
		exampleForList.setPage(page);
		exampleForList.setOrderByClause("id desc");
		
		sysmanRoleService.getPanigationByRowBounds(exampleForCount, exampleForList);
		
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
