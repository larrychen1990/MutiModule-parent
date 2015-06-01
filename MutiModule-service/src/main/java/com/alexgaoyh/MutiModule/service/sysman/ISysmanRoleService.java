package com.alexgaoyh.MutiModule.service.sysman;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleExample;
import com.alexgaoyh.MutiModule.persist.util.Pagination;

/**
 * 
 * @desc ISysmanRoleService e接口
 *
 * @author alexgaoyh
 */
public interface ISysmanRoleService {

	/**
	 * 插入操作
	 * @param SysmanRole sysmanRole 插入操作实体类数据
	 */
	public void insert(SysmanRole sysmanRole);
	
	/**
	 * 插入操作，根据实体类的相关参数，匹配插入数据
	 * @param SysmanRole sysmanRole 插入操作实体类数据
	 */
	public void insertSelective(SysmanRole sysmanRole);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public SysmanRole selectByPrimaryKey(Integer id);
	
	/**
	 * 根据主键id 有选择的更新实体信息
	 * @param SysmanRole sysmanRole 根据主键id，有选择的更新操作实体类数据
	 */
	public int updateByPrimaryKeySelective(SysmanRole sysmanRole);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param example 过滤条件
	 * @return 此条件下共有多少条匹配数据
	 */
	public int countByExample(SysmanRoleExample example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param example 过滤条件
	 * @return 此条件下返回的数据list集合
	 */
	public List<SysmanRole> selectByExample(SysmanRoleExample example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param SysmanRoleExample 过滤条件
	 * @return 分页信息
	 */
	Pagination<SysmanRole> getPanigationByRowBounds(SysmanRoleExample example);
	
	/**
	 * 根据example查询出相关的数据信息，并将这些数据信息进行更新，更新参数如record
	   @param record 更新的值
	 * @param example 过滤条件
	 * @return 符合条件的数据条数
	 */
	public int updateByExampleSelective(SysmanRole record, SysmanRoleExample example);
	
	/**
	 * 根据example删除出相关的数据集，并对其进行删除操作
	 * @param example 过滤条件
	 * @return 符合条件的删除数据条数
	 */
	public int deleteByExample(SysmanRoleExample example);
	
	/**
     * 根据用户id，获取这个用户所包含的所有角色信息集合
     * @param id 用户id
     * @return  返回这个用户id所包含的角色集合
     */
    List<SysmanRole> selectRoleListBySysmanUserId(Integer id);
    
    /**
	 * 根据ids数组，逻辑删除对象
	 * @param deleteFlag
	 * @param ids
	 * @return
	 */
	public int deleteLogicByIds(Integer deleteFlag, Integer[] ids);
}
