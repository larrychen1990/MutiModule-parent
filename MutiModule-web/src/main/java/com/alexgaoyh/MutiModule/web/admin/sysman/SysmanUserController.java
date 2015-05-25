package com.alexgaoyh.MutiModule.web.admin.sysman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;


@Controller
@RequestMapping(value="admin/sysmanUser")
public class SysmanUserController {

	private ISysmanUserService sysmanUserService;

	// service　层 get/set 方法
	public ISysmanUserService getSysmanUserService() {
		return sysmanUserService;
	}

	public void setSysmanUserService(ISysmanUserService sysmanUserService) {
		this.sysmanUserService = sysmanUserService;
	}
	// service 层get/set 方法
	
	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value="login")
	public ModelAndView login() {
		return new ModelAndView("admin/login");
	}
	
}
