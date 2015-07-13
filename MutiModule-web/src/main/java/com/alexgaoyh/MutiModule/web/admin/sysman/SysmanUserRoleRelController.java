package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.util.List;

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

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser.SysmanUser;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleService;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserRoleRelService;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanUserService;
import com.alexgaoyh.MutiModule.web.util.JSONUtilss;
import com.alexgaoyh.MutiModule.web.util.Result;

@Controller
@RequestMapping(value = "admin/sysmanUserRoleRel")
public class SysmanUserRoleRelController {

	@Resource
	private ISysmanUserRoleRelService sysmanUserRoleRelService;

	@Resource
	private ISysmanRoleService sysmanRoleService;

	@Resource
	private ISysmanUserService sysmanUserService;

	/**
	 * 通用方法 后台list页面 如请求地址为： http://localhost:8080/web/sysmanRole/list 则返回的页面应该在
	 * /web/WEB-INF/views/sysmanRole/list.jsp
	 * 
	 * @return
	 */
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

		List<SysmanRole> selectedList = selectSysmanRoleListByUserId(Integer
				.parseInt(request.getParameter("id")));
		List<SysmanRole> allList = selectSysmanRoleList();
		// 求差集，待选区域为未被选中的数据集合
		List<SysmanRole> _allListTemp = allList;
		_allListTemp.removeAll(selectedList);
		// 已选区域，未选区域 已选区域+未选区域=全部数据
		model.addObject("selectedList", selectedList);
		model.addObject("unselectedList", _allListTemp);

		Integer id = Integer.parseInt(request.getParameter("id"));
		SysmanUser sUser = sysmanUserService.selectByPrimaryKey(id);

		model.addObject("object", sUser);

		model.setViewName(moduleName + "/list");
		model.addObject("moduleName", moduleName);
		return model;
	}

	/**
	 * 保存关联关系
	 * 
	 * @param request
	 *            请求报文
	 * @param response
	 *            响应报文
	 * @param objectId
	 *            sysmanUser用户id
	 * @param relIds
	 *            sysmanRole角色id 字符串，','分隔
	 * @throws Exception
	 */
	@RequestMapping(value = "saveRels")
	@ResponseBody
	public void saveRels(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("objectId") Integer objectId,
			@RequestParam("relIds") String relIds) throws Exception {
		Result result = null;

		try {
			sysmanUserRoleRelService.removeOldRelAndSaveNewRel(objectId, relIds);
			result = new Result(true, "保存成功！");
		} catch (Exception e) {
			result = new Result(false, "保存失败！" + e.getMessage());
		} finally {
			JSONUtilss.renderJson(result, response);
		}
	}

	/**
	 * 根据当前用户，获取到这个用户所包含的用户角色
	 * 
	 * @param sysmanUserId
	 *            当前sysmanUser的用户id
	 * @return
	 */
	private List<SysmanRole> selectSysmanRoleListByUserId(Integer sysmanUserId) {
		return sysmanRoleService.selectRoleListBySysmanUserId(sysmanUserId);
	}

	/**
	 * 获取当前所有的用户角色信息
	 * 
	 * @return
	 */
	private List<SysmanRole> selectSysmanRoleList() {
		return sysmanRoleService.selectByExample(null);
	}

}
