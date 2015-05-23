package com.alexgaoyh.MutiModule.service.${packageName};

import java.util.List;

import com.alexgaoyh.MutiModule.persist.${packageName}.${className};
import com.alexgaoyh.MutiModule.persist.${packageName}.${className}Example;
import com.alexgaoyh.MutiModule.persist.util.Pagination;

/**
 * 
 * @desc I${className}Service e接口
 *
 * @author alexgaoyh
 */
public interface I${className}Service {

	/**
	 * 插入操作
	 * @param ${className} ${smallClassName} 插入操作实体类数据
	 */
	public void insert(${className} ${smallClassName});
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public ${className} selectByPrimaryKey(Integer id);
	
	/**
	 * 根据主键id 有选择的更新实体信息
	 * @param ${className} ${smallClassName} 根据主键id，有选择的更新操作实体类数据
	 */
	public int updateByPrimaryKeySelective(${className} ${smallClassName});
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 *  @param ${className}Example 过滤条件
	 * @return 此条件下共有多少条匹配数据
	 */
	public int countByExample(${className}Example example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 *  @param ${className}Example 过滤条件
	 * @return 此条件下返回的数据list集合
	 */
	public List<${className}> selectByExample(${className}Example example);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param ${className}Example 过滤条件
	 * @return 分页信息
	 */
	Pagination<${className}> getPanigationByRowBounds(${className}Example example);
	
}
