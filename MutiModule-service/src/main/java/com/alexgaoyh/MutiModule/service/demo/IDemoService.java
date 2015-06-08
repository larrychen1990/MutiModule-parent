package com.alexgaoyh.MutiModule.service.demo;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.persist.util.Pagination;

/**
 * 
 * @desc IDemoService e接口
 *
 * @author alexgaoyh
 */
public interface IDemoService {

	/**
	 * 插入操作
	 * @param demo
	 */
	public void insert(Demo demo);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public Demo selectByPrimaryKey(Integer id);
	
	/**
	 * 根据入参信息（包含分页页码，过滤条件）返回符合条件的数据信息
	 * @param demoExample 过滤条件
	 * @return 分页信息
	 */
	Pagination<Demo> getPanigationByRowBounds(DemoExample example);
	
}
