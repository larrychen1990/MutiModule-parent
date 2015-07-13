<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manager</title>

	<jsp:include page="../common/adminCommon.jsp"></jsp:include>	

</head>
<body>
	<table id="dg-1" class="easyui-datagrid" title="列表" style="width: 700px; height: 300px"
		data-options="toolbar:'#toolbar-1',checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,fitColumns:true,url:'${pageContext.request.contextPath}/${moduleName}/getData',method:'get',pagination:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:80">编码</th>
				<th data-options="field:'name',width:80">名称</th>
				<th data-options="field:'createTime',width:80,formatter:function(value){var date=parseDate(value);return date;}">创建时间</th>
				<th data-options="field:'deleteFlag',width:80,formatter:function(value){
						if(value==0){
							return '未删除';
						}else if(value==1){
								return '已删除';
						}
				}">删除状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar-1">
		<a href="#" class="easyui-linkbutton add" iconCls="icon-add" plain="true">新增</a> 
		<a href="#" class="easyui-linkbutton edit" iconCls="icon-edit" plain="true">修改</a> 
		<a href="#" class="easyui-linkbutton remove" iconCls="icon-remove" plain="true">删除</a>
		<a href="#" class="easyui-linkbutton releation" iconCls="icon-add" plain="true">包含资源</a>
	</div>
	
	<div id="dlg-1" class="easyui-dialog" title="数据参数" style="z-Index: 100px;" fit="true" closed="true" buttons="#dlg-buttons-1">
		<form method="post">
			<table cellpadding="5">
				<tr>
					<td><input type="hidden" name="id" /></td>
				</tr>
	    		<tr>
	    			<td>名称:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>描述:</td>
	    			<td><input class="easyui-textbox" type="text" name="description" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
		</form>
	</div>
	
	<div id="dlg-buttons-1">
		<a href="#" class="easyui-linkbutton  save" iconCls="icon-ok">保存</a> 
		<a href="#" class="easyui-linkbutton cancel" iconCls="icon-cancel">取消</a>
	</div>
	
	<script type="text/javascript">
		$( function() {
			var dg1 = new DataGridEasyui(context_, 1 , templateUrl, 'crud');
			
			$.extend(dg1, {
				init : function() {
					DataGridEasyui.prototype.init.call(this);
					//add 关联关系处理 begin
					this.toolBar.find(".releation").bind('click', this.proxy(function(){
						var rows = this.dataGrid.datagrid('getSelections');
						if (!rows || rows.length == 0) {
							$.messager.alert('提示', '请选择记录！');
						} else {
							if (rows.length == 1) {
								document.location.href = this.context + "/admin/sysmanRoleResourceRel/list?id="+rows[0].id;
							} else {
								$.messager.alert('提示', '请选择单行记录！');
							}
						}
					},this,this.toolBar.find(".releation")));
					//add 关联关系处理 end
				}
			});
			
			dg1.init();
		});
	</script>
	
</body>
</html>