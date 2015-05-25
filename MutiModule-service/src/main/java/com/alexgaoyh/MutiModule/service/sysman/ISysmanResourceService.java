package com.alexgaoyh.MutiModule.service.sysman;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResourceExample;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.persist.util.TreeNode;

/**
 * 
 * @desc ISysmanResourceService e接口
 *
 * @author alexgaoyh
 */
public interface ISysmanResourceService {

	/**
	 * 插入操作
	 * @param SysmanResource sysmanResource 插入操作实体类数据
	 */
	public void insert(SysmanResource sysmanResource);
	
	/**
	 * 插入操作，根据实体类的相关参数，匹配插入数据
	 * @param SysmanResource sysmanResource 插入操作实体类数据
	 */
	public void insertSelective(SysmanResource sysmanResource);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public SysmanResource selectByPrimaryKey(Integer id);
	
	/**
	 * 根据主键id 有选择的更新实体信息
	 * @param SysmanResource sysmanResource 根据主键id，有选择的更新操作实体类数据
	 */
	public int updateByPrimaryKeySelective(SysmanResource sysmanResource);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param example 过滤条件
	 * @return 此条件下共有多少条匹配数据
	 */
	public int countByExample(SysmanResourceExample example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param example 过滤条件
	 * @return 此条件下返回的数据list集合
	 */
	public List<SysmanResource> selectByExample(SysmanResourceExample example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param SysmanResourceExample 过滤条件
	 * @return 分页信息
	 */
	Pagination<SysmanResource> getPanigationByRowBounds(SysmanResourceExample example);
	
	/**
	 * 根据example查询出相关的数据信息，并将这些数据信息进行更新，更新参数如record
	   @param record 更新的值
	 * @param example 过滤条件
	 * @return 符合条件的数据条数
	 */
	public int updateByExampleSelective(SysmanResource record, SysmanResourceExample example);
	
	/**
	 * 根据example删除出相关的数据集，并对其进行删除操作
	 * @param example 过滤条件
	 * @return 符合条件的删除数据条数
	 */
	public int deleteByExample(SysmanResourceExample example);
	
	/**
     * 根据角色id，获取到这个角色下所包含的资源集合
     * @param id 角色id
     * @return 符合条件的资源list集合
     */
    List<SysmanResource> selectResourceListBySysmanRoleId(Integer id);
    
    /**
     * 查询SysmanResource表结构中，parent_id为空的资源集合
     * @return parent_id 为空的资源集合
     */
    List<SysmanResource> selectTopSysmanResourceByParentId();
    
    /**
     * 根据当前id。获取这个id对应资源的属性结构数据
     * @param id sysmanResource表中的主键id
     * @return 对应主键id下的属性结构
     */
    List<TreeNode> selectTreeNodeBySysmanResourceId(Integer id);
	
}
