package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRel;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRelExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRelMapper;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleResourceRelService;

/**
 * 
 * @desc ISysmanRoleResourceRelService 接口实现类
 *
 * @author alexgaoyh
 */
public class SysmanRoleResourceRelServiceImpl implements ISysmanRoleResourceRelService {

	private SysmanRoleResourceRelMapper sysmanRoleResourceRelMapper;

	//------------------get set方法 begin
	public SysmanRoleResourceRelMapper getSysmanRoleResourceRelMapper() {
		return sysmanRoleResourceRelMapper;
	}

	public void setSysmanRoleResourceRelMapper(SysmanRoleResourceRelMapper sysmanRoleResourceRelMapper) {
		this.sysmanRoleResourceRelMapper = sysmanRoleResourceRelMapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(SysmanRoleResourceRel sysmanRoleResourceRel) {
		sysmanRoleResourceRelMapper.insert(sysmanRoleResourceRel);
	}
	
	@Override
	public void insertSelective(SysmanRoleResourceRel sysmanRoleResourceRel) {
		sysmanRoleResourceRelMapper.insertSelective(sysmanRoleResourceRel);
	}

	@Override
	public int updateByExampleSelective(SysmanRoleResourceRel record, SysmanRoleResourceRelExample example) {
		return sysmanRoleResourceRelMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanRoleResourceRelExample example) {
		return sysmanRoleResourceRelMapper.deleteByExample(example);
	}
}
