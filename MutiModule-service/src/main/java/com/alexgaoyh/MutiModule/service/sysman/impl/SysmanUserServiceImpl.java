package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.MutiModule.common.vo.Pagination;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUserExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUserMapper;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;

/**
 * 
 * @desc ISysmanUserService 接口实现类
 *
 * @author alexgaoyh
 */
@Service(value = "sysmanUserService")
public class SysmanUserServiceImpl implements ISysmanUserService {

	@Resource(name = "sysmanUserMapper")
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
	public Pagination<SysmanUser> getPanigationByRowBounds(SysmanUserExample exampleForCount, SysmanUserExample exampleForList) {
		
		int count = sysmanUserMapper.countByExample(exampleForCount);
		List<SysmanUser> list = sysmanUserMapper.selectByExample(exampleForList);
		
		return new Pagination<SysmanUser>( count, list);
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

	@Override
	public int deleteLogicByIds(Integer deleteFlag, Integer[] ids) {
		return sysmanUserMapper.deleteLogicByIds(deleteFlag, ids);
	}
}
