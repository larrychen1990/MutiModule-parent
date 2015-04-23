package com.alexgaoyh.MutiModule.service.demo.impl;

import java.util.List;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.demo.IDemoService;

public class DemoServiceImpl implements IDemoService{
	
	private DemoMapper demoMapper;

	//------------------get set方法 begin
	public DemoMapper getDemoMapper() {
		return demoMapper;
	}

	public void setDemoMapper(DemoMapper demoMapper) {
		this.demoMapper = demoMapper;
	}
	//------------------get set方法 end

	@Override
	public void insert(Demo demo) {
		demoMapper.insert(demo);
	}

	@Override
	public Demo selectByPrimaryKey(Integer id) {
		return demoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Pagination<Demo> getPanigationByRowBounds(DemoExample example) {
		
		int count = demoMapper.countByExample(example);
		List<Demo> list = demoMapper.selectByExample(example);
		
		return new Pagination(example.getMyRowBounds(), count, list);
	}

}
