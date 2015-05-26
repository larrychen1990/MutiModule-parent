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
	public void insertSelective(SysmanUser sysmanUser) {
		sysmanUserMapper.insertSelective(sysmanUser);
	}

	@Override
	public SysmanUser selectByPrimaryKey(Integer id) {
		return sysmanUserMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysmanUser sysmanUser) {
		return sysmanUserMapper.updateByPrimaryKeySelective(sysmanUser);
	}
	
	@Override
	public int countByExample(SysmanUserExample example) {
		return sysmanUserMapper.countByExample(example);
	}
	
	@Override
	public List<SysmanUser> selectByExample(SysmanUserExample example) {
		return sysmanUserMapper.selectByExample(example);
	}

	@Override
	public Pagination<SysmanUser> getPanigationByRowBounds(SysmanUserExample example) {
		
		int count = sysmanUserMapper.countByExample(example);
		List<SysmanUser> list = sysmanUserMapper.selectByExample(example);
		
		return new Pagination<SysmanUser>(example.getMyRowBounds(), count, list);
	}

	@Override
	public int updateByExampleSelective(SysmanUser record, SysmanUserExample example) {
		return sysmanUserMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanUserExample example) {
		return sysmanUserMapper.deleteByExample(example);
	}

	@Override
	public SysmanUser selectUserByNameAndPasswd(String name, String passWord) {
		return sysmanUserMapper.selectUserByNameAndPasswd(name, passWord);
	}
}
