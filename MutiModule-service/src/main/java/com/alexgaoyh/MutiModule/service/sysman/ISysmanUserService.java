package com.alexgaoyh.MutiModule.service.sysman;

import java.util.List;

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
	 * @param SysmanUser sysmanUser 插入操作实体类数据
	 */
	public void insert(SysmanUser sysmanUser);
	
	/**
	 * 插入操作，根据实体类的相关参数，匹配插入数据
	 * @param SysmanUser sysmanUser 插入操作实体类数据
	 */
	public void insertSelective(SysmanUser sysmanUser);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public SysmanUser selectByPrimaryKey(Integer id);
	
	/**
	 * 根据主键id 有选择的更新实体信息
	 * @param SysmanUser sysmanUser 根据主键id，有选择的更新操作实体类数据
	 */
	public int updateByPrimaryKeySelective(SysmanUser sysmanUser);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param example 过滤条件
	 * @return 此条件下共有多少条匹配数据
	 */
	public int countByExample(SysmanUserExample example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param example 过滤条件
	 * @return 此条件下返回的数据list集合
	 */
	public List<SysmanUser> selectByExample(SysmanUserExample example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param SysmanUserExample 过滤条件
	 * @return 分页信息
	 */
	Pagination<SysmanUser> getPanigationByRowBounds(SysmanUserExample example);
	
	/**
	 * 根据example查询出相关的数据信息，并将这些数据信息进行更新，更新参数如record
	   @param record 更新的值
	 * @param example 过滤条件
	 * @return 符合条件的数据条数
	 */
	public int updateByExampleSelective(SysmanUser record, SysmanUserExample example);
	
}
