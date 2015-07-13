package com.alexgaoyh.MutiModule.persist.sysman.SysmanResource;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.MutiModule.common.vo.TreeNode;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResourceExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResourceMapper;

public class SysmanResourceMybatisTest {

	private SysmanResourceMapper sysmanResourceMapper;
	
	@Before
    public void prepare() throws Exception {
    	
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "mybatis-spring-config.xml" );
        
        sysmanResourceMapper = (SysmanResourceMapper) ctx.getBean( "sysmanResourceMapper" );
        
    }
	
	//@Test
	public void testInsert() {
		
		try {
			SysmanResource sysmanResource = new SysmanResource();
			sysmanResource.setDeleteFlag(0);
			sysmanResource.setName("alexgaoyh");
			sysmanResource.setCreateTime(new Date());
			sysmanResource.setDescription("alexgaoyh");
			sysmanResourceMapper.insert(sysmanResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testSysmanResourceExample() {
		SysmanResourceExample sysmanResourceExample = new SysmanResourceExample();
		sysmanResourceExample.setOrderByClause("id desc");
		
		int count1 = sysmanResourceMapper.countByExample(sysmanResourceExample);
		List<SysmanResource> SysmanResourceList1 = sysmanResourceMapper.selectByExample(sysmanResourceExample);
		
		System.out.println("count1 = " + count1);
		for(SysmanResource sysmanResource : SysmanResourceList1) {
			System.out.println("SysmanResource.getId() = " + sysmanResource.getId());
		}
		
	}
	
	//@Test
	public void getTreeNode() {
		List<SysmanResource> topResourceList = sysmanResourceMapper.selectTopSysmanResourceByParentId();
		for(SysmanResource sysmanResource : topResourceList) {
			List<TreeNode> treeNodeList = sysmanResourceMapper.selectTreeNodeBySysmanResourceId(sysmanResource.getId());
			
			treeMenuList(treeNodeList);
		}
	}
	
	
	private void treeMenuList(List<TreeNode> root) {
		for (TreeNode node : root) {
			System.out.println("当前节点：" + node.getId());
			if(node.getChildren() != null) {
				treeMenuList(node.getChildren());
			}
		}
	}
	
	//@Test
	public void getSysmanResourceByParentId() {
		sysmanResourceMapper.selectSysmanResourceListByParentId(1);
	}
}
