package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResourceExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResourceMapper;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;

/**
 * 
 * @desc ISysmanResourceService 接口实现类
 *
 * @author alexgaoyh
 */
public class SysmanResourceServiceImpl implements ISysmanResourceService {

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
		
		return new Pagination<SysmanResource>(example.getMyRowBounds(), count, list);
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
}
