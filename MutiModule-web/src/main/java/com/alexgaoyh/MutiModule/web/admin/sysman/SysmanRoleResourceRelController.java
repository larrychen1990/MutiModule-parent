package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.util.ArrayList;
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

import com.alexgaoyh.MutiModule.persist.sysman.SysmanResource.SysmanResource;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRole;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleResourceRelService;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleService;
import com.alexgaoyh.MutiModule.util.jackson.JacksonUtil;
import com.alexgaoyh.MutiModule.web.util.JSONUtilss;
import com.alexgaoyh.MutiModule.web.util.Result;

@Controller
@RequestMapping(value = "admin/sysmanRoleResourceRel")
public class SysmanRoleResourceRelController {

	@Resource
	private ISysmanRoleResourceRelService sysmanRoleResourceRelService;

	@Resource
	private ISysmanResourceService sysmanResourceService;
	
	@Resource
	private ISysmanRoleService sysmanRoleService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response, ModelAndView model) {

		// moduleName 部分 begin
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
		// moduleName 部分 end
		
		Integer sysmanRoleId = Integer.parseInt(request.getParameter("id"));
		SysmanRole sRole = sysmanRoleService.selectByPrimaryKey(sysmanRoleId);
		model.addObject("object", sRole);
		
		model.setViewName(moduleName + "/list");
		model.addObject("moduleName", moduleName);
		return model;
	}
	
	@RequestMapping(value="getMenus")
    @ResponseBody
	public String getMenus(HttpServletRequest request,HttpServletResponse response) {
		
		Integer sysmanRoleId = Integer.parseInt(request.getParameter("id"));
		List<SysmanResource> sysResourceList = sysmanResourceService.selectTopSysmanResourceByParentId();
		
		List<Map<String, Object>> map = resourceToTree(sysmanRoleId, sysResourceList);
		
		return JacksonUtil.toJSon(map);
	}
	
	/**
	 * 转换为tree类型
	 * @param sysmanRoleId	角色id
	 * @param sysResourceList  资源list集合
	 * @return
	 */
	private List<Map<String, Object>> resourceToTree(Integer sysmanRoleId, List<SysmanResource> sysResourceList) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for(SysmanResource r : sysResourceList){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", r.getId());
			item.put("text", r.getName());
			
			List<SysmanResource> children = sysmanResourceService.selectSysmanResourceListByParentId(r.getId());
			if (children != null && children.size() != 0) {
				item.put("children", this.resourceToTree(sysmanRoleId, children));
			}
			
			Boolean checkSelected = sysmanRoleResourceRelService.checkRoleIdResourceIdExisted(sysmanRoleId, r.getId());
			if(checkSelected) {
				item.put("checked", true);
			}
			
			data.add(item);
		}
		return data;
	}
	
	@RequestMapping(value = "saveRels")
	@ResponseBody
	public void saveRels(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("objectId") Integer objectId,
			@RequestParam("relIds") String relIds) throws Exception {
		Result result = null;

		try {
			sysmanRoleResourceRelService.removeOldRelAndSaveNewRel(objectId, relIds);
			result = new Result(true, "保存成功！");
		} catch (Exception e) {
			result = new Result(false, "保存失败！" + e.getMessage());
		} finally {
			JSONUtilss.renderJson(result, response);
		}
	}
}
