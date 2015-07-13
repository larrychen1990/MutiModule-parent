package com.alexgaoyh.MutiModule.service.sysman;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel.SysmanUserRoleRelExample;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserRoleRel.SysmanUserRoleRelKey;

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
	public void insert(SysmanUserRoleRelKey sysmanUserRoleRel);
	
	/**
	 * 插入操作，根据实体类的相关参数，匹配插入数据
	 * @param SysmanUserRoleRel sysmanUserRoleRel 插入操作实体类数据
	 */
	public void insertSelective(SysmanUserRoleRelKey sysmanUserRoleRel);
	
	/**
	 * 根据example查询出相关的数据信息，并将这些数据信息进行更新，更新参数如record
	   @param record 更新的值
	 * @param example 过滤条件
	 * @return 符合条件的数据条数
	 */
	public int updateByExampleSelective(SysmanUserRoleRelKey record, SysmanUserRoleRelExample example);
	
	/**
	 * 根据example删除出相关的数据集，并对其进行删除操作
	 * @param example 过滤条件
	 * @return 符合条件的删除数据条数
	 */
	public int deleteByExample(SysmanUserRoleRelExample example);
	
	/**
	 * 根据sysmanUserId 用户id，先删除此用户的所有角色信息，之后根据sysmanRoleIds（ids字符串），将新的关系保存起来
	 * @param sysmanUserId	sysmanUser用户id
	 * @param sysmanRoleIds	sysmanRole角色id集合，','分隔字符串
	 * @return
	 */
	public void removeOldRelAndSaveNewRel(Integer sysmanUserId, String sysmanRoleIds);
	
}
