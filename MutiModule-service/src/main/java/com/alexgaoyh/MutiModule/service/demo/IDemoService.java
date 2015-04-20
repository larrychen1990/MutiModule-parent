package com.alexgaoyh.MutiModule.service.demo;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;

public interface IDemoService {

	/**
	 * 插入操作
	 * @param demo
	 */
	public void insertDemo(DemoEntity demo);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public DemoEntity selectDemoById(Integer id);
	
	/**
	 * 向不存在的表结构中插入数据
	 * @param demo
	 */
	public void insertDemoForTransaction(DemoEntity demo);
}
