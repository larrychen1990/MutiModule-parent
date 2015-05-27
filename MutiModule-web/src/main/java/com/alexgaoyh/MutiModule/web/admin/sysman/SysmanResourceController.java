package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexgaoyh.MutiModule.persist.sysman.SysmanUser;
import com.alexgaoyh.MutiModule.persist.util.TreeNode;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;
import com.alexgaoyh.MutiModule.util.jackson.JacksonUtil;
import com.alexgaoyh.MutiModule.util.redis.RedisClient;
import com.alexgaoyh.MutiModule.web.util.ConstantsUtil;
import com.alexgaoyh.MutiModule.web.util.Result;

@Controller
@RequestMapping(value="admin/sysmanResource")
public class SysmanResourceController {

	@Resource
	private ISysmanResourceService sysmanResourceService;
	
	@RequestMapping(value="getMenus")
    @ResponseBody
	public String getMenus() {
		List<TreeNode> treeNodeList = sysmanResourceService.selectTreeNodeBySysmanResourceId(1);
		
		return JacksonUtil.toJSon(treeNodeList);
	}
	
	@RequestMapping(value="checkSysmanResourcePermission", method = RequestMethod.POST)
	@ResponseBody
	public String checkSysmanResourcePermission(@RequestParam("sysmanResourceId") Integer sysmanResourceId) {
		Result result = null;
		
		SysmanUser su = JacksonUtil.readValue(RedisClient.get(ConstantsUtil.ADMIN_LOGIN_CONSTANTS), SysmanUser.class);
		
		Boolean isPermitted = sysmanResourceService.checkSysmanResourcePermission(sysmanResourceId, su.getId());
		
		if(isPermitted) {
			result = new Result(true, "包含权限");
		}else{
			result = new Result(false, "不包含权限");
		}
		return JacksonUtil.toJSon(result);
	}
}
