package com.alexgaoyh.MutiModule.web.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.MutiModule.common.utils.PaginationUtil;
import com.MutiModule.common.vo.Pagination;
import com.MutiModule.common.vo.mybatis.pagination.Page;
import com.alexgaoyh.MutiModule.persist.demo.Demo;
import com.alexgaoyh.MutiModule.persist.demo.DemoExample;
import com.alexgaoyh.MutiModule.service.demo.IDemoService;

@Controller
@RequestMapping(value="demo")
public class DemoController {
	
	Logger logger = LoggerFactory.getLogger(DemoController.class);
	
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
		demo.setDeleteFlag(0);
		demoService.insert(demo);
		return "demo/index";
	}
	
	@RequestMapping(value = "/page/{id}")
	public ModelAndView blogItem(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
		
		logger.debug("XXXXXX");
		logger.info("XXXXXX");
		logger.warn("XXXXXX");
		logger.error("XXXXXX");
		
		Map map = new HashMap();
		
		DemoExample demoExampleForCount = new DemoExample();
		demoExampleForCount.setOrderByClause("id asc");
		
		DemoExample demoExampleForList = new DemoExample();
		demoExampleForList.setOrderByClause("id asc");
		
		int pageNumber = 1;
		int pageSize = 1;
		Page page = new Page(PaginationUtil.startValue(pageNumber, pageSize), pageSize);
		demoExampleForList.setPage(page);
		
		Pagination<Demo> pagination = demoService.getPanigationByRowBounds(demoExampleForCount, demoExampleForList);
		
		map.put("pagination", pagination);
		
		ModelAndView mav = new ModelAndView("demo/page", map);

		return mav;
	}
	
	
}
