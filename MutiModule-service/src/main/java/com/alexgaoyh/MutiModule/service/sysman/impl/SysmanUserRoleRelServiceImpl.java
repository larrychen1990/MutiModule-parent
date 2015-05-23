package com.alexgaoyh.MutiModule.service.sysman.impl;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRelExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRelMapper;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserRoleRelService;

/**
 * 
 * @desc ISysmanUserRoleRelService 接口实现类
 *
 * @author alexgaoyh
 */
public class SysmanUserRoleRelServiceImpl implements ISysmanUserRoleRelService {

	private SysmanUserRoleRelMapper sysmanUserRoleRelMapper;

	//------------------get set方法 begin
	public SysmanUserRoleRelMapper getSysmanUserRoleRelMapper() {
		return sysmanUserRoleRelMapper;
	}

	public void setSysmanUserRoleRelMapper(SysmanUserRoleRelMapper sysmanUserRoleRelMapper) {
		this.sysmanUserRoleRelMapper = sysmanUserRoleRelMapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(SysmanUserRoleRel sysmanUserRoleRel) {
		sysmanUserRoleRelMapper.insert(sysmanUserRoleRel);
	}
	
	@Override
	public void insertSelective(SysmanUserRoleRel sysmanUserRoleRel) {
		sysmanUserRoleRelMapper.insertSelective(sysmanUserRoleRel);
	}

	@Override
	public int updateByExampleSelective(SysmanUserRoleRel record, SysmanUserRoleRelExample example) {
		return sysmanUserRoleRelMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanUserRoleRelExample example) {
		return sysmanUserRoleRelMapper.deleteByExample(example);
	}
}
