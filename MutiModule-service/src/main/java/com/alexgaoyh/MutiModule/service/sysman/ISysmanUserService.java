package com.alexgaoyh.MutiModule.service.sysman;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUserExample;
import com.alexgaoyh.MutiModule.persist.util.Pagination;

/**
 * 
 * @desc ISysmanUserService e接口
 *
 * @author alexgaoyh
 */
public interface ISysmanUserService {

	/**
	 * 插入操作
	 * @param sysmanUserExample 过滤条件
	 */
	public void insert(SysmanUser sysmanUser);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public SysmanUser selectByPrimaryKey(Integer id);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param SysmanUserExample 过滤条件
	 * @return 分页信息
	 */
	Pagination<SysmanUser> getPanigationByRowBounds(SysmanUserExample example);
}