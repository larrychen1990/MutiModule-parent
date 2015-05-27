package com.alexgaoyh.MutiModule.web.admin.sysman;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexgaoyh.MutiModule.persist.util.TreeNode;
import com.alexgaoyh.MutiModule.service.sysman.ISysmanResourceService;
import com.alexgaoyh.MutiModule.util.jackson.JacksonUtil;

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
}
