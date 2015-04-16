package com.alexgaoyh.MutiModule.web.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;
import com.alexgaoyh.MutiModule.service.demo.IDemoService;

@Controller
@RequestMapping(value="demo")
public class DemoController {
	
	@Resource
	private IDemoService demoService;

	//demoService get/set 方法  begin
	public IDemoService getDemoService() {
		return demoService;
	}

	public void setDemoService(IDemoService demoService) {
		this.demoService = demoService;
	}
	//demoService get/set 方法  end
	

	@RequestMapping("index")
	public String index() {
		DemoEntity demo = new DemoEntity();
		demo.setName("demo/index");
		demoService.insertDemo(demo);
		return "demo/index";
	}
	
}
