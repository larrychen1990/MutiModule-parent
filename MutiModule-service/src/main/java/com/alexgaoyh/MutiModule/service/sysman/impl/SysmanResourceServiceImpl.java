package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.MutiModule.common.vo.Pagination;
import com.MutiModule.common.vo.TreeNode;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResourceExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResourceMapper;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;

/**
 * 
 * @desc ISysmanResourceService 接口实现类
 *
 * @author alexgaoyh
 */
@Service(value = "sysmanResourceService")
public class SysmanResourceServiceImpl implements ISysmanResourceService {

	@Resource(name = "sysmanResourceMapper")
	private SysmanResourceMapper sysmanResourceMapper;

	//------------------get set方法 begin
	public SysmanResourceMapper getSysmanResourceMapper() {
		return sysmanResourceMapper;
	}

	public void setSysmanResourceMapper(SysmanResourceMapper sysmanResourceMapper) {
		this.sysmanResourceMapper = sysmanResourceMapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(SysmanResource sysmanResource) {
		sysmanResourceMapper.insert(sysmanResource);
	}
	
	@Override
	public void insertSelective(SysmanResource sysmanResource) {
		sysmanResourceMapper.insertSelective(sysmanResource);
	}

	@Override
	public SysmanResource selectByPrimaryKey(Integer id) {
		return sysmanResourceMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysmanResource sysmanResource) {
		return sysmanResourceMapper.updateByPrimaryKeySelective(sysmanResource);
	}
	
	@Override
	public int countByExample(SysmanResourceExample example) {
		return sysmanResourceMapper.countByExample(example);
	}
	
	@Override
	public List<SysmanResource> selectByExample(SysmanResourceExample example) {
		return sysmanResourceMapper.selectByExample(example);
	}

	@Override
	public Pagination<SysmanResource> getPanigationByRowBounds(SysmanResourceExample example) {
		
		int count = sysmanResourceMapper.countByExample(example);
		List<SysmanResource> list = sysmanResourceMapper.selectByExample(example);
		
		return new Pagination<SysmanResource>(count, list);
	}

	@Override
	public int updateByExampleSelective(SysmanResource record, SysmanResourceExample example) {
		return sysmanResourceMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanResourceExample example) {
		return sysmanResourceMapper.deleteByExample(example);
	}

	@Override
	public List<SysmanResource> selectResourceListBySysmanRoleId(Integer id) {
		return sysmanResourceMapper.selectResourceListBySysmanRoleId(id);
	}

	@Override
	public List<SysmanResource> selectTopSysmanResourceByParentId() {
		return sysmanResourceMapper.selectTopSysmanResourceByParentId();
	}

	@Override
	public List<TreeNode> selectTreeNodeBySysmanResourceId(Integer id) {
		return sysmanResourceMapper.selectTreeNodeBySysmanResourceId(id);
	}

	@Override
	public Boolean checkSysmanResourcePermission(Integer sysmanResourceId, Integer sysmanUserId) {
		return sysmanResourceMapper.checkSysmanResourcePermission(sysmanResourceId, sysmanUserId);
	}

	@Override
	public int deleteLogicByIds(Integer deleteFlag, Integer[] ids) {
		return sysmanResourceMapper.deleteLogicByIds(deleteFlag, ids);
	}

	@Override
	public List<SysmanResource> selectSysmanResourceListByParentId(
			Integer parentId) {
		return sysmanResourceMapper.selectSysmanResourceListByParentId(parentId);
	}
	
}
