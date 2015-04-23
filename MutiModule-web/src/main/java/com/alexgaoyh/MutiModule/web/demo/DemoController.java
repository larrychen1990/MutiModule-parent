package com.alexgaoyh.MutiModule.web.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
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
	
	@RequestMapping(value = "/page/{id}")
	public ModelAndView blogItem(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
		
		DemoExample example = new DemoExample();
		example.setOrderByClause("id desc");
		
		MyRowBounds myRowBounds = new MyRowBounds(id,10);
		example.setMyRowBounds(myRowBounds);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Pagination<Demo> pagination = demoService.getPanigationByRowBounds(example);
		
		map.put("pagination", pagination);
		
		ModelAndView mav = new ModelAndView("demo/page", map);

		return mav;
	}
	
	
}
