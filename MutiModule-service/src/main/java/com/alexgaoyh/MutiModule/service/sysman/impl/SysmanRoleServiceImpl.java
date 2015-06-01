package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleMapper;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleService;

/**
 * 
 * @desc ISysmanRoleService 接口实现类
 *
 * @author alexgaoyh
 */
public class SysmanRoleServiceImpl implements ISysmanRoleService {

	private SysmanRoleMapper sysmanRoleMapper;

	//------------------get set方法 begin
	public SysmanRoleMapper getSysmanRoleMapper() {
		return sysmanRoleMapper;
	}

	public void setSysmanRoleMapper(SysmanRoleMapper sysmanRoleMapper) {
		this.sysmanRoleMapper = sysmanRoleMapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(SysmanRole sysmanRole) {
		sysmanRoleMapper.insert(sysmanRole);
	}
	
	@Override
	public void insertSelective(SysmanRole sysmanRole) {
		sysmanRoleMapper.insertSelective(sysmanRole);
	}

	@Override
	public SysmanRole selectByPrimaryKey(Integer id) {
		return sysmanRoleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysmanRole sysmanRole) {
		return sysmanRoleMapper.updateByPrimaryKeySelective(sysmanRole);
	}
	
	@Override
	public int countByExample(SysmanRoleExample example) {
		return sysmanRoleMapper.countByExample(example);
	}
	
	@Override
	public List<SysmanRole> selectByExample(SysmanRoleExample example) {
		return sysmanRoleMapper.selectByExample(example);
	}

	@Override
	public Pagination<SysmanRole> getPanigationByRowBounds(SysmanRoleExample example) {
		
		int count = sysmanRoleMapper.countByExample(example);
		List<SysmanRole> list = sysmanRoleMapper.selectByExample(example);
		
		return new Pagination<SysmanRole>(example.getMyRowBounds(), count, list);
	}

	@Override
	public int updateByExampleSelective(SysmanRole record, SysmanRoleExample example) {
		return sysmanRoleMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanRoleExample example) {
		return sysmanRoleMapper.deleteByExample(example);
	}

	@Override
	public List<SysmanRole> selectRoleListBySysmanUserId(Integer id) {
		return sysmanRoleMapper.selectRoleListBySysmanUserId(id);
	}

	@Override
	public int deleteLogicByIds(Integer deleteFlag, Integer[] ids) {
		return sysmanRoleMapper.deleteLogicByIds(deleteFlag, ids);
	}
}
