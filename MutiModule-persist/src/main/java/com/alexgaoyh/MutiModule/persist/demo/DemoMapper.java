package com.alexgaoyh.MutiModule.persist.demo;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;

public interface DemoMapper {

	/**
	 * 插入操作
	 * 返回受影响的条数信息，如果想要获取到主键信息，直接取demo.getId();即可
	 * @param demo
	 */
	public Integer insertDemo(DemoEntity demo);
	
	/**
	 * 根据id获取实体信息
	 * @param id
	 */
	public DemoEntity selectDemoById(Integer id);
	
	/**
	 * 向不存在的表结构中插入数据
	 * @param demo
	 */
	public void insertIntoNotExistTable(DemoEntity demo);
	
}
