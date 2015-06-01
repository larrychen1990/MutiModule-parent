package com.alexgaoyh.MutiModule.persist.sysman;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alexgaoyh.MutiModule.persist.util.TreeNode;

public interface SysmanResourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int countByExample(SysmanResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int deleteByExample(SysmanResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int insert(SysmanResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int insertSelective(SysmanResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    List<SysmanResource> selectByExample(SysmanResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    SysmanResource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysmanResource record, @Param("example") SysmanResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int updateByExample(@Param("record") SysmanResource record, @Param("example") SysmanResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int updateByPrimaryKeySelective(SysmanResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanresource
     *
     * @mbggenerated Sat May 23 12:18:15 CST 2015
     */
    int updateByPrimaryKey(SysmanResource record);
    
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
    
    /**
     * 查询sysmanUserId用户是否有操作sysmanResourceId的权限
     * @param sysmanResourceId	资源id
     * @param sysmanUserId	用户id
     * @return
     */
    Boolean checkSysmanResourcePermission(@Param("sysmanResourceId")Integer sysmanResourceId, 
    		@Param("sysmanUserId")Integer sysmanUserId);
    
    /**
   	 * 根据ids数组，逻辑删除对象
   	 * @param ids
   	 * @return
   	 */
   	public int deleteLogicByIds(@Param("deleteflag")Integer deleteFlag, @Param("ids")Integer[] ids);
   	
   	/**
   	 * 根据parentid返回符合条件的孩子节点集合
   	 * @param parentId	父亲id
   	 * @return
   	 */
   	List<SysmanResource> selectSysmanResourceListByParentId(Integer parentId);
}