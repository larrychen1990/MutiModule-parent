package com.alexgaoyh.MutiModule.service.demo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.MutiModule.common.vo.Pagination;
import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;
import com.alexgaoyh.MutiModule.service.demo.IDemoService;

/**
 * 
 * @desc IDemoService 接口实现类
 *
 * @author alexgaoyh
 */
@Service(value = "demoService")
public class DemoServiceImpl implements IDemoService {

	@Resource(name = "demoMapper")
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
	public int insert(Demo demo) {
		return demoMapper.insert(demo);
	}

	@Override
	public Demo selectByPrimaryKey(Integer id) {
		return demoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Pagination<Demo> getPanigationByRowBounds(DemoExample exampleForCount, DemoExample exampleForList) {
		
		int count = demoMapper.countByExample(exampleForCount);
		List<Demo> list = demoMapper.selectByExample(exampleForList);
		
		System.out.println("count= " + count);
		System.out.println("list= " + list.size());
		
		return new Pagination(count, list);
	}

}
