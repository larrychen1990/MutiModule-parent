package com.alexgaoyh.MutiModule.service.demo;

import com.alexgaoyh.MutiModule.persist.demo.Demo;

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
	
}
