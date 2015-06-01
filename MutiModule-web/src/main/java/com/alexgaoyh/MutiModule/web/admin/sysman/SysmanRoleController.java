package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanRole;
import com.alexgaoyh.MutiModule.persist.sysman.SysmanRoleExample;
import com.alexgaoyh.MutiModule.persist.util.MyRowBounds;
import com.alexgaoyh.MutiModule.persist.util.Pagination;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanRoleService;
import com.alexgaoyh.MutiModule.web.admin.vo.EasyUIData;
import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;
import com.alexgaoyh.MutiModule.web.util.JSONUtilss;
import com.alexgaoyh.MutiModule.web.util.Result;
import com.alexgaoyh.MutiModule.web.util.StringUtilss;

@Controller
@RequestMapping(value="admin/sysmanRole")
public class SysmanRoleController {

	@Resource
	private ISysmanRoleService sysmanRoleService;
	
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
		
		SysmanRoleExample example = this.encloseObject(request);
		
		Pagination<SysmanRole> pagination = sysmanRoleService.getPanigationByRowBounds(example);
		
		EasyUIData data = new EasyUIData(pagination);
		
		JSONUtilss.renderJson(data, response);
	}
	
	/** 通用方法
	 * 根据入参封装对象
	 * @param request 请求对象
	 * @return 请求对象包含的所有过滤条件进行过滤
	 */
	private SysmanRoleExample encloseObject(HttpServletRequest request) {
		SysmanRoleExample example = new SysmanRoleExample();
		
		String page = request.getParameter("page");//easyui datagrid 分页 页号
		String rows = request.getParameter("rows");//easyui datagrid 分页 页数
		if(StringUtilss.isInteger(rows) && StringUtilss.isInteger(page)) {
			MyRowBounds myRowBounds = new MyRowBounds(Integer.parseInt(page),Integer.parseInt(rows));
			example.setMyRowBounds(myRowBounds);
		}
		
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
	public String save(HttpServletRequest request, HttpServletResponse response, SysmanRole entity) throws Exception {
		Result result = null;
		try {
			beforeDoSave(request, entity);
			sysmanRoleService.insert(entity);
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
	protected void beforeDoSave(HttpServletRequest request, SysmanRole entity) throws Exception {
		entity.setDeleteflag(ConstantsUtil.DELETE_NO);
		entity.setCreatetime(new Date());
	}
	
	/** 通用方法 
	 * 电泳保存方法之后进行的方法调用
	 * @param request
	 * @param entity 对应实体信息
	 * @throws Exception
	 */
	protected void afterDoSave(HttpServletRequest request, SysmanRole entity) throws Exception {
		
	}
	
	/**
	 * 更新
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="doUpdate")
    @ResponseBody
	public String doUpdate(HttpServletRequest request, HttpServletResponse response, SysmanRole entity) throws IOException {
		Result result = null;
		try {
			beforeDoUpdate(request, entity);
			sysmanRoleService.updateByPrimaryKeySelective(entity);
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
	protected void beforeDoUpdate(HttpServletRequest request, SysmanRole entity) throws Exception {
		
	}
	
	/**
	 * 调用更新操作之后进行的操作
	 * @param request
	 * @param entity
	 * @throws Exception
	 */
	protected void afterDoUpdate(HttpServletRequest request, SysmanRole entity) throws Exception {
		
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
				int deleteCount = sysmanRoleService.deleteLogicByIds(ConstantsUtil.DELETE_YES, StringUtilss.stringToIntegerArray(pids.split("::")));
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
