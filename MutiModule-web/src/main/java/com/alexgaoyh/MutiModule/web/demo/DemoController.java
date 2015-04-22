package com.alexgaoyh.MutiModule.web.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
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
		Demo demo = new Demo();
		demo.setName("demo/index");
		demo.setDeleteflagstate(0);
		demoService.insert(demo);
		return "demo/index";
	}
	
	
}
