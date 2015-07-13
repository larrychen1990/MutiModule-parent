package com.alexgaoyh.MutiModule.web.demo;

import java.util.Date;
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
	
	/**
	 * demoService.insert(demo) 进行插入方法，之后插入第二条数据，name字段使用demo.getId()主键内容
	 * 测试关联关系的处理
	 * @return
	 */
	@RequestMapping(value = "insertRel")
	public ModelAndView insertRel() {
		
		Map map = new HashMap();
		
		Demo demo = new Demo();
		demo.setDeleteFlag(0);
		demo.setCreateTime(new Date());
		demo.setName("demo/index");
		demoService.insert(demo);
		
		Demo demo2 = new Demo();
		demo2.setDeleteFlag(0);
		demo2.setCreateTime(new Date());
		demo2.setName(demo.getId() + "");
		demoService.insert(demo2);
		
		ModelAndView mav = new ModelAndView("demo/page", map);

		return mav;
	}
	
	
}
