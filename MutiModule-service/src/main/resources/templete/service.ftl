package com.alexgaoyh.MutiModule.service.${packageName};

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
	 * @param ${smallClassName}Example 过滤条件
	 */
	public void insert(${className} ${smallClassName});
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public ${className} selectByPrimaryKey(Integer id);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param ${className}Example 过滤条件
	 * @return 分页信息
	 */
	Pagination<${className}> getPanigationByRowBounds(${className}Example example);
}
