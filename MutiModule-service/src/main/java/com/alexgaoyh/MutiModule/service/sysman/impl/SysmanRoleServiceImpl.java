package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.MutiModule.common.vo.Pagination;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRoleExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRoleMapper;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleService;

/**
 * 
 * @desc ISysmanRoleService 接口实现类
 *
 * @author alexgaoyh
 */
@Service(value = "sysmanRoleService")
public class SysmanRoleServiceImpl implements ISysmanRoleService {

	@Resource(name = "sysmanRoleMapper")
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
	public Pagination<SysmanRole> getPanigationByRowBounds(SysmanRoleExample exampleForCount, SysmanRoleExample exampleForList) {
		
		int count = sysmanRoleMapper.countByExample(exampleForCount);
		List<SysmanRole> list = sysmanRoleMapper.selectByExample(exampleForList);
		
		return new Pagination<SysmanRole>(count, list);
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
