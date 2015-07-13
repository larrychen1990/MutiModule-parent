package com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleResourceRel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysmanRoleResourceRelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int countByExample(SysmanRoleResourceRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int deleteByExample(SysmanRoleResourceRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int deleteByPrimaryKey(SysmanRoleResourceRelKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int insert(SysmanRoleResourceRelKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int insertSelective(SysmanRoleResourceRelKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    List<SysmanRoleResourceRelKey> selectByExample(SysmanRoleResourceRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysmanRoleResourceRelKey record, @Param("example") SysmanRoleResourceRelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sysmanroleresourcerel
     *
     * @mbggenerated Mon Jul 13 09:08:32 CST 2015
     */
    int updateByExample(@Param("record") SysmanRoleResourceRelKey record, @Param("example") SysmanRoleResourceRelExample example);
    
    /**
     * 根据sysmanUserId删除关联关系
     * @param sysmanRoleId	sysmanRoleId表中id，角色id
     * @return
     */
    int deleteByRoleId(@Param("sysmanRoleId")Integer sysmanRoleId);
    
    /**
     * 批量插入数据
     * @param list	SysmanRoleResourceRel对象的集合
     * @return
     */
    int insertbatch(List<SysmanRoleResourceRelKey> list);
    
    /**
     * 檢查當前sysmanRoleId角色id和sysmanResourceId資源id是否存在
     * @param sysmanRoleId
     * @param sysmanResourceId
     * @return
     */
    Boolean checkRoleIdResourceIdExisted(@Param("sysmanRoleId")Integer sysmanRoleId,
    		@Param("sysmanResourceId")Integer sysmanResourceId);
}