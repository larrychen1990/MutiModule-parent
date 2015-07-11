package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.MutiModule.common.vo.TreeNode;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;
import com.alexgaoyh.MutiModule.util.jackson.JacksonUtil;
import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;
import com.alexgaoyh.MutiModule.web.util.JSONUtilss;
import com.alexgaoyh.MutiModule.web.util.Result;
import com.alexgaoyh.MutiModule.web.util.StringUtilss;

@Controller
@RequestMapping(value="admin/sysmanResource")
public class SysmanResourceController {

	@Resource
	private ISysmanResourceService sysmanResourceService;
	
	@Resource
	private ISysmanUserService sysmanUserService;
	
	/**	通用方法
	 * 后台list页面
	 * 如请求地址为：   	http://localhost:8080/web/sysmanResource/list
	 * 则返回的页面应该在    /web/WEB-INF/views/sysmanResource/list.jsp
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
		
		List<SysmanResource> sysResourceList = sysmanResourceService.selectTopSysmanResourceByParentId();
		
		JSONUtilss.renderJson(resourceToTree(sysResourceList), response);
	}
	
	/**
	 * 转换为tree类型
	 * @param sysResourceList  资源list集合
	 * @return
	 */
	private List<Map<String, Object>> resourceToTree(List<SysmanResource> sysResourceList) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for(SysmanResource r : sysResourceList){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("name", r.getName());
			item.put("description", r.getDescription());
			
			if(StringUtilss.isNotEmpty(r.getParentid() + "") && !"null".equals(r.getParentid() + "") ) {
				SysmanResource parent = sysmanResourceService.selectByPrimaryKey(r.getParentid());
				item.put("parent.id",parent.getId());
				item.put("parent.name",parent.getName());
			}
			else {
				item.put("parent.id","");
				item.put("parent.name","");
			}
			List<SysmanResource> children = sysmanResourceService.selectSysmanResourceListByParentId(r.getId());
			if (children != null && children.size() != 0) {
				item.put("children", this.resourceToTree(children));
				item.put("leaf", false);
			}else{
				item.put("leaf", true);
			}
			item.put("href", r.getHref());
			item.put("id", r.getId());
			item.put("deleteFlag", r.getDeleteflag());
			item.put("createTime", r.getCreatetime());
			
			data.add(item);
		}
		return data;
	}
	
	@RequestMapping(value="getMenus")
    @ResponseBody
	public String getMenus() {
		List<TreeNode> treeNodeList = sysmanResourceService.selectTreeNodeBySysmanResourceId(1);
		
		return JacksonUtil.toJSon(treeNodeList);
	}
	
	@RequestMapping(value="checkSysmanResourcePermission", method = RequestMethod.POST)
	@ResponseBody
	public String checkSysmanResourcePermission(HttpServletRequest request, @RequestParam("sysmanResourceId") Integer sysmanResourceId) {
		Result result = null;
		
		//從web.xml裡面，取出context-param鍵值對標註的某個值
        String adminLoginCookieName = request.getServletContext().getInitParameter("adminLoginCookieName");
        
		String sysmanUserId = CookieUtilss.get(request, adminLoginCookieName);
		
		SysmanUser su = sysmanUserService.selectByPrimaryKey(Integer.parseInt(sysmanUserId));
		
		Boolean isPermitted = sysmanResourceService.checkSysmanResourcePermission(sysmanResourceId, su.getId());
		
		if(isPermitted) {
			result = new Result(true, sysmanResourceService.selectByPrimaryKey(sysmanResourceId).getHref());
		}else{
			result = new Result(false, "不包含权限");
		}
		return JacksonUtil.toJSon(result);
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
	public String save(HttpServletRequest request, HttpServletResponse response, SysmanResource entity) throws Exception {
		Result result = null;
		try {
			beforeDoSave(request, entity);
			sysmanResourceService.insert(entity);
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
	protected void beforeDoSave(HttpServletRequest request, SysmanResource entity) throws Exception {
		entity.setDeleteflag(ConstantsUtil.DELETE_NO);
		entity.setCreatetime(new Date());
	}
	
	/** 通用方法 
	 * 电泳保存方法之后进行的方法调用
	 * @param request
	 * @param entity 对应实体信息
	 * @throws Exception
	 */
	protected void afterDoSave(HttpServletRequest request, SysmanResource entity) throws Exception {
		
	}
	
	/**
	 * 更新
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="doUpdate")
    @ResponseBody
	public String doUpdate(HttpServletRequest request, HttpServletResponse response, SysmanResource entity) throws IOException {
		Result result = null;
		try {
			beforeDoUpdate(request, entity);
			sysmanResourceService.updateByPrimaryKeySelective(entity);
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
	protected void beforeDoUpdate(HttpServletRequest request, SysmanResource entity) throws Exception {
		
	}
	
	/**
	 * 调用更新操作之后进行的操作
	 * @param request
	 * @param entity
	 * @throws Exception
	 */
	protected void afterDoUpdate(HttpServletRequest request, SysmanResource entity) throws Exception {
		
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
				int deleteCount = sysmanResourceService.deleteLogicByIds(ConstantsUtil.DELETE_YES, StringUtilss.stringToIntegerArray(pids.split("::")));
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
