package com.alexgaoyh.MutiModule.service.sysman.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRel.SysmanRoleResourceRelExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRel.SysmanRoleResourceRelKey;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRel.SysmanRoleResourceRelMapper;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleResourceRelService;

/**
 * 
 * @desc ISysmanRoleResourceRelService 接口实现类
 *
 * @author alexgaoyh
 */
@Service(value = "sysmanRoleResourceRelService")
public class SysmanRoleResourceRelServiceImpl implements ISysmanRoleResourceRelService {

	@Resource(name = "sysmanRoleResourceRelMapper")
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
	public void insert(SysmanRoleResourceRelKey sysmanRoleResourceRel) {
		sysmanRoleResourceRelMapper.insert(sysmanRoleResourceRel);
	}
	
	@Override
	public void insertSelective(SysmanRoleResourceRelKey sysmanRoleResourceRel) {
		sysmanRoleResourceRelMapper.insertSelective(sysmanRoleResourceRel);
	}

	@Override
	public int updateByExampleSelective(SysmanRoleResourceRelKey record, SysmanRoleResourceRelExample example) {
		return sysmanRoleResourceRelMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int deleteByExample(SysmanRoleResourceRelExample example) {
		return sysmanRoleResourceRelMapper.deleteByExample(example);
	}

	@Override
	public void removeOldRelAndSaveNewRel(Integer sysmanRoleId,
			String sysmanResourceIds) {
		sysmanRoleResourceRelMapper.deleteByRoleId(sysmanRoleId);
		
		String[] sysmanResourceIdsArray = sysmanResourceIds.split(",");
		int sysmanResourceIdsLength = sysmanResourceIdsArray.length;
		
		List<SysmanRoleResourceRelKey> list = new ArrayList<SysmanRoleResourceRelKey>();
		for(int i = 0; i < sysmanResourceIdsLength; i++) {
			SysmanRoleResourceRelKey srrr = new SysmanRoleResourceRelKey();
			srrr.setSysmanRoleId(sysmanRoleId);
			srrr.setSysmanResourceId(Integer.parseInt(sysmanResourceIdsArray[i].trim()));
			list.add(srrr);
		}
		
		sysmanRoleResourceRelMapper.insertbatch(list);
	}

	@Override
	public Boolean checkRoleIdResourceIdExisted(Integer sysmanRoleId,
			Integer sysmanResourceId) {
		return sysmanRoleResourceRelMapper.checkRoleIdResourceIdExisted(sysmanRoleId, sysmanResourceId);
	}
}
