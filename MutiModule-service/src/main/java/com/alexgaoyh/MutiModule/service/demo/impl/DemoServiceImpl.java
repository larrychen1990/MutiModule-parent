package com.alexgaoyh.MutiModule.service.demo.impl;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;
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
	public void insertDemo(DemoEntity demo) {
		demoMapper.insertDemo(demo);
	}

	@Override
	public DemoEntity selectDemoById(Integer id) {
		return demoMapper.selectDemoById(id);
	}

}
