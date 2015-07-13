package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel.SysmanUserRoleRelExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel.SysmanUserRoleRelKey;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel.SysmanUserRoleRelMapper;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserRoleRelService;

/**
 * 
 * @desc ISysmanUserRoleRelService 接口实现类
 *
 * @author alexgaoyh
 */
@Service(value = "sysmanUserRoleRelService")
public class SysmanUserRoleRelServiceImpl implements ISysmanUserRoleRelService {

	@Resource(name = "sysmanUserRoleRelMapper")
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
	public void insert(SysmanUserRoleRelKey sysmanUserRoleRel) {
		sysmanUserRoleRelMapper.insert(sysmanUserRoleRel);
	}
	
	@Override
	public void insertSelective(SysmanUserRoleRelKey sysmanUserRoleRel) {
		sysmanUserRoleRelMapper.insertSelective(sysmanUserRoleRel);
	}

	@Override
	public int updateByExampleSelective(SysmanUserRoleRelKey record, SysmanUserRoleRelExample example) {
		return sysmanUserRoleRelMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanUserRoleRelExample example) {
		return sysmanUserRoleRelMapper.deleteByExample(example);
	}

	@Override
	public void removeOldRelAndSaveNewRel(Integer sysmanUserId,
			String sysmanRoleIds) {
		sysmanUserRoleRelMapper.deleteByUserId(sysmanUserId);
		
		String[] sysmanRoleIdsArray = sysmanRoleIds.split(",");
		int sysmanRoleIdsLength = sysmanRoleIdsArray.length;
		
		List<SysmanUserRoleRelKey> list = new ArrayList<SysmanUserRoleRelKey>();
		for(int i = 0; i < sysmanRoleIdsLength; i++) {
			SysmanUserRoleRelKey surr = new SysmanUserRoleRelKey();
			surr.setSysmanUserId(sysmanUserId);
			surr.setSysmanRoleId(Integer.parseInt(sysmanRoleIdsArray[i].trim()));
			list.add(surr);
		}
		
		sysmanUserRoleRelMapper.insertbatch(list);
		
		
	}
}
