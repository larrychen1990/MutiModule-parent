package com.alexgaoyh.MutiModule.service.sysman;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRelExample;

/**
 * 
 * @desc ISysmanUserRoleRelService e接口
 *
 * @author alexgaoyh
 */
public interface ISysmanUserRoleRelService {

	/**
	 * 插入操作
	 * @param SysmanUserRoleRel sysmanUserRoleRel 插入操作实体类数据
	 */
	public void insert(SysmanUserRoleRel sysmanUserRoleRel);
	
	/**
	 * 插入操作，根据实体类的相关参数，匹配插入数据
	 * @param SysmanUserRoleRel sysmanUserRoleRel 插入操作实体类数据
	 */
	public void insertSelective(SysmanUserRoleRel sysmanUserRoleRel);
	
	/**
	 * 根据example查询出相关的数据信息，并将这些数据信息进行更新，更新参数如record
	   @param record 更新的值
	 * @param example 过滤条件
	 * @return 符合条件的数据条数
	 */
	public int updateByExampleSelective(SysmanUserRoleRel record, SysmanUserRoleRelExample example);
	
	/**
	 * 根据example删除出相关的数据集，并对其进行删除操作
	 * @param example 过滤条件
	 * @return 符合条件的删除数据条数
	 */
	public int deleteByExample(SysmanUserRoleRelExample example);
	
}
