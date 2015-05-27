package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;
import com.alexgaoyh.MutiModule.util.jackson.JacksonUtil;
import com.alexgaoyh.MutiModule.util.redis.RedisClient;
import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;
import com.alexgaoyh.MutiModule.web.util.StringUtilss;


@Controller
@RequestMapping(value="admin/sysmanUser")
public class SysmanUserController {

	@Resource
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
	
	/**
	 * 登陆方法
	 * @param userName	用户名
	 * @param passWord 密码
	 * @param captcha 验证码
	 * @return
	 */
	@RequestMapping(value="doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(@RequestParam("userName") String userName, 
			@RequestParam("passWord") String passWord, @RequestParam("captcha") String captcha) {
		
		ModelAndView mv = new ModelAndView();
		
		if(StringUtilss.isNotEmpty(userName) && StringUtilss.isNotEmpty(passWord) && StringUtilss.isNotEmpty(captcha)) {
			if(captcha.equals(RedisClient.get(ConstantsUtil.ADMIN_CAPTCHA_CONSTANTS))) {
				SysmanUser su = sysmanUserService.selectUserByNameAndPasswd(userName, passWord);
				if(su != null) {
					//用户信息加入缓存
					RedisClient.add(ConstantsUtil.ADMIN_LOGIN_CONSTANTS, JacksonUtil.toJSon(su), ConstantsUtil.EXPIRE_TIME_30_MINUTE);
					//登陆成功，页面重定向
					mv.setViewName("redirect:manager");
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("errorMsg", "用户名密码错误!");
					mv.addObject("mapInfo", map);
					mv.setViewName("admin/loginError");
				}
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("errorMsg", "验证码错误!");
				mv.addObject("mapInfo", map);
				mv.setViewName("admin/loginError");
			}
			
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("errorMsg", "请输入完整信息!");
			mv.addObject("mapInfo", map);
			mv.setViewName("admin/loginError");
		}
		
		
		return mv;
	}
	
	/**
	 * 后台登陆首页信息
	 * @return
	 */
	@RequestMapping(value="manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("sysmanUser", JacksonUtil.readValue(RedisClient.get(ConstantsUtil.ADMIN_LOGIN_CONSTANTS), SysmanUser.class));
		
		return new ModelAndView("admin/manager", map);
	}
}
