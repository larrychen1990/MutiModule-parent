package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.MutiModule.common.utils.CookieUtilss;
import com.MutiModule.common.utils.JSONUtilss;
import com.MutiModule.common.utils.MD5Util;
import com.MutiModule.common.utils.PaginationUtil;
import com.MutiModule.common.utils.StringUtilss;
import com.MutiModule.common.vo.EasyUIData;
import com.MutiModule.common.vo.Pagination;
import com.MutiModule.common.vo.Result;
import com.MutiModule.common.vo.mybatis.pagination.Page;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUser;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUserExample;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;
import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;


@Controller
@RequestMapping(value="admin/sysmanUser")
public class SysmanUserController {

	@Resource
	private ISysmanUserService sysmanUserService;

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
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("userName") String userName, 
			@RequestParam("passWord") String passWord) {
		
		ModelAndView mv = new ModelAndView();
		
		if(StringUtilss.isNotEmpty(userName) && StringUtilss.isNotEmpty(passWord)) {
			
			SysmanUser su = sysmanUserService.selectUserByNameAndPasswd(userName, MD5Util.encrypByMd5Jar(passWord));
			if(su != null) {
				
				CookieUtilss.add(response, request.getServletContext().getInitParameter("adminLoginCookieName"), su.getId() + "", 30*60);
				
				
				//加入缓存
				//RedisClient.add(SysmanUser.class.getName() + "_" + su.getId(), JacksonUtil.toJSon(su));
				
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
	public ModelAndView manager(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//從web.xml裡面，取出context-param鍵值對標註的某個值
        String adminLoginCookieName = request.getServletContext().getInitParameter("adminLoginCookieName");
        
		String sysmanUserId = CookieUtilss.get(request, adminLoginCookieName);
		
		//map.put("sysmanUser", JacksonUtil.readValue(RedisClient.get(SysmanUser.class.getName() + "_" + sysmanUserId), SysmanUser.class));
		map.put("sysmanUser", sysmanUserService.selectByPrimaryKey(Integer.parseInt(sysmanUserId)));
		
		return new ModelAndView("admin/manager", map);
	}
	
	/**
	 * 登出方法
	 * @return
	 */
	@RequestMapping(value="logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		//删除登陆的缓存信息
		//RedisClient.del(ConstantsUtil.ADMIN_LOGIN_CONSTANTS);
		
		//從web.xml裡面，取出context-param鍵值對標註的某個值
        String adminLoginCookieName = request.getServletContext().getInitParameter("adminLoginCookieName");
        CookieUtilss.remove(request, response, adminLoginCookieName);
        
		
		return new ModelAndView("redirect:login");
	}
	
	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied() {
		return new ModelAndView("admin/denied");
	}
	
	/**	通用方法
	 * 后台list页面
	 * 如请求地址为：   	http://localhost:8080/web/sysmanRole/list
	 * 则返回的页面应该在    /web/WEB-INF/views/sysmanRole/list.jsp
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		RequestMapping rm = this.getClass().getAnnotation(RequestMapping.class);
		String moduleName = "";
		if (rm != null) {
			String[] values = rm.value();
			if (ArrayUtils.isNotEmpty(values)) {
				moduleName = values[0];
			}
		}
		if (moduleName.endsWith("/")) {
			moduleName = moduleName.substring(0, moduleName.length() - 1);
		}
		
		model.setViewName(moduleName + "/list");
		model.addObject("moduleName", moduleName);
		return model;
	}
	
	/** 通用方法
	 * 后台页面渲染easyui-datagrid 方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getData")
    @ResponseBody
	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		SysmanUserExample exampleForCount = this.encloseObjectForCount(request);
		SysmanUserExample exampleForList = this.encloseObjectForList(request);
		
		
		Pagination<SysmanUser> pagination = sysmanUserService.getPanigationByRowBounds(exampleForCount, exampleForList);
		
		EasyUIData data = new EasyUIData(pagination);
		
		JSONUtilss.renderJson(data, response);
	}
	
	/** 通用方法
	 * 根据入参封装对象
	 * @param request 请求对象
	 * @return 请求对象包含的所有过滤条件进行过滤
	 */
	private SysmanUserExample encloseObjectForCount(HttpServletRequest request) {
		SysmanUserExample example = new SysmanUserExample();
		
		//TODO 添加相关过滤条件筛选的功能
		CriteriaConditions(request, example);
		
		return example;
	}
	
	/** 通用方法
	 * 根据入参封装对象
	 * @param request 请求对象
	 * @return 请求对象包含的所有过滤条件进行过滤
	 */
	private SysmanUserExample encloseObjectForList(HttpServletRequest request) {
		SysmanUserExample example = new SysmanUserExample();
		
		//TODO 添加相关过滤条件筛选的功能
		CriteriaConditions(request, example);
		
		example.setOrderByClause("id asc");
		
		Integer pageNumber = Integer.parseInt(request.getParameter("page"));//easyui datagrid 分页 页号
		Integer pageSize = Integer.parseInt(request.getParameter("rows"));//easyui datagrid 分页 页数
		Page page = new Page(PaginationUtil.startValue(pageNumber, pageSize), pageSize);
		example.setPage(page);
		
		return example;
	}
	
	private SysmanUserExample CriteriaConditions(HttpServletRequest request, SysmanUserExample example) {
		//TODO 过滤条件筛选，后期可根据需求手动书写
		return example;
	}
	
	/** 通用方法 
	 * 新增，保存
	 * @param request
	 * @param response
	 * @param bean 请求对象的bean
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="doSave")
    @ResponseBody
	public String save(HttpServletRequest request, HttpServletResponse response, SysmanUser entity) throws Exception {
		Result result = null;
		try {
			beforeDoSave(request, entity);
			sysmanUserService.insert(entity);
			afterDoSave(request, entity);
			result = new Result(true, "保存成功！");
		} catch (Exception e) {
			result = new Result(false, "保存失败！"+e.getMessage());
		} finally {
			JSONUtilss.renderJson(result, response);
		}

		return null;
	}
	
	/** 通用方法 
	 * 调用保存方法之前进行的方法调用
	 * @param request
	 * @param entity 对应实体信息
	 * @throws Exception
	 */
	protected void beforeDoSave(HttpServletRequest request, SysmanUser entity) throws Exception {
		entity.setDeleteFlag(ConstantsUtil.DELETE_NO);
		entity.setCreateTime(new Date());
		entity.setPassword(MD5Util.encrypByMd5Jar("admin"));
	}
	
	/** 通用方法 
	 * 电泳保存方法之后进行的方法调用
	 * @param request
	 * @param entity 对应实体信息
	 * @throws Exception
	 */
	protected void afterDoSave(HttpServletRequest request, SysmanUser entity) throws Exception {
		
	}
	
	/**
	 * 更新
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="doUpdate")
    @ResponseBody
	public String doUpdate(HttpServletRequest request, HttpServletResponse response, SysmanUser entity) throws IOException {
		Result result = null;
		try {
			beforeDoUpdate(request, entity);
			sysmanUserService.updateByPrimaryKeySelective(entity);
			afterDoUpdate(request, entity);
			result = new Result(true, "更新成功！");
		} catch (Exception e) {
			result = new Result(false, "更新失败！"+e.getMessage());
		} finally {
			JSONUtilss.renderJson(result, response);
		}
		return null;
	}

	/**
	 * 调用更新操作之前进行的操作
	 * @param request
	 * @param entity
	 * @throws Exception
	 */
	protected void beforeDoUpdate(HttpServletRequest request, SysmanUser entity) throws Exception {
		
	}
	
	/**
	 * 调用更新操作之后进行的操作
	 * @param request
	 * @param entity
	 * @throws Exception
	 */
	protected void afterDoUpdate(HttpServletRequest request, SysmanUser entity) throws Exception {
		
	}
	
	/** 通用方法 
	 * 删除
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="logicDelete")
    @ResponseBody
	public String logicDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Result result = null;
		try {
			String pids = request.getParameter("ids");
			if (pids != null) {
				int deleteCount = sysmanUserService.deleteLogicByIds(ConstantsUtil.DELETE_YES, StringUtilss.stringToIntegerArray(pids.split("::")));
				result = new Result(true, "删除成功！操作" + deleteCount + "条数据");
			} else {
				result = new Result(false, "没有参数，非法操作！");
			}
		} catch (Exception e) {
			result = new Result(false, "更新失败！"+e.getMessage());
		} finally {
			JSONUtilss.renderJson(result, response);
		}
		return null;
	}
}
