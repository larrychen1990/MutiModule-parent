package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserMapper;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;

/**
 * 
 * @desc ISysmanUserService 接口实现类
 *
 * @author alexgaoyh
 */
public class SysmanUserServiceImpl implements ISysmanUserService {

	private SysmanUserMapper sysmanUserMapper;

	//------------------get set方法 begin
	public SysmanUserMapper getSysmanUserMapper() {
		return sysmanUserMapper;
	}

	public void setSysmanUserMapper(SysmanUserMapper sysmanUserMapper) {
		this.sysmanUserMapper = sysmanUserMapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(SysmanUser sysmanUser) {
		sysmanUserMapper.insert(sysmanUser);
	}

	@Override
	public SysmanUser selectByPrimaryKey(Integer id) {
		return sysmanUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public Pagination<SysmanUser> getPanigationByRowBounds(SysmanUserExample example) {
		
		int count = sysmanUserMapper.countByExample(example);
		List<SysmanUser> list = sysmanUserMapper.selectByExample(example);
		
		return new Pagination(example.getMyRowBounds(), count, list);
	}

}