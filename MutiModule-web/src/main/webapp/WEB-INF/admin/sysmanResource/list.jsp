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
	<table id="dg-1" class="easyui-treegrid" title="列表" style="width: 700px; height: 300px"
		data-options="toolbar:'#toolbar-1',idField:'id',treeField:'name',checkOnSelect:true,selectOnCheck:true,fit:true,rownumbers:true,fitColumns:true,url:'${pageContext.request.contextPath}/${moduleName}/getData',method:'get',pagination:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id'" width="10">编码</th>
				<th data-options="field:'name'" width="220">名称</th>
				<th data-options="field:'href'" width="220">链接</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar-1">
		<a href="#" class="easyui-linkbutton add" iconCls="icon-add" plain="true">新增</a> 
		<a href="#" class="easyui-linkbutton edit" iconCls="icon-edit" plain="true">修改</a> 
		<a href="#" class="easyui-linkbutton remove" iconCls="icon-remove" plain="true">删除</a>
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
				<tr>
					<td>链接:</td>
					<td><input class="easyui-textbox" type="text" name="href" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>父知识点:</td>
					<td><input class="easyui-combotree" name="parent.id" id ="parentId" style="width:280px;" data-options="method:'get'" /> </td>
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
			
			//前端获取到的list集合，转化为easyui-combotree所需的treenode
			function listToTreeNode(value){
				if(value.children){
					return {id:value.id,text:value.name,children:$.map(value.children,listToTreeNode)};
				}else{
					return {id:value.id,text:value.name};
				}
				
			}
			
			
			var dg1 = new DataGridEasyui(context_, 1 , templateUrl, 'crud');
			
			$.extend(dg1, {
				add : function() {

					DataGridEasyui.prototype.add.call(this);

					$("#parentId").combotree("loadData",this.dataGrid.treegrid("getData").map(listToTreeNode));
					
					
				},
				reload : function() {
					
					DataGridEasyui.prototype.reload.call(this);
					
					this.dataGrid.treegrid('reload');	// reload the all rows
				},
				edit : function() {

					DataGridEasyui.prototype.edit.call(this);
					
					var row = this.dataGrid.datagrid('getSelected');
					var resourceTree = $("#parentId");
					resourceTree.combotree("loadData",this.dataGrid.treegrid("getData").map(listToTreeNode));
					resourceTree.combotree("setValue",row['parent.id']);
					resourceTree.combotree("setText",row['parent.name']);
				}

			});
			
			
			dg1.init();
		});
	</script>
	
</body>
</html>