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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<jsp:include page="../common/adminCommon.jsp"></jsp:include>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/sysmanRel/index.css">
</head>
<body>
	<div id="toolbar-1">
		<a href="javascript:history.go(-1);" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" id="saveClick">保存</a>
	</div>
	<div><p>当前用户信息： 编号：${object.id }; 姓名： ${object.name }</p></div>
	<div class="select_side">
		<p>待选区</p>
		<select id="selectL" name="selectL" multiple="multiple">
			<c:forEach var="unselected" items="${unselectedList}" varStatus="status">
				<option value=" ${unselected.id} ">${unselected.name }--${unselected.id}</option>
			</c:forEach>
		</select>
	</div>
	<div class="select_opt">
		<p id="toright" title="添加">></p>
		<p id="toleft" title="移除"><</p>
	</div>
	<div class="select_side">
		<p>已选区</p>
		<select id="selectR" name="selectR" multiple="multiple">
			<c:forEach var="selected" items="${selectedList}" varStatus="status">
				<option value="${selected.id}">${selected.name }--${selected.id}</option>
			</c:forEach>
		</select>
	</div>

	<script type="text/javascript">
		var objectId = ${object.id};
		var leftSel = $("#selectL");
		var rightSel = $("#selectR");
		//绑定向右的方向建按钮的click事件，当单击按钮时，左侧列表框选中的项会添加到右侧列表框中，完成添加的操作。
		$("#toright").bind("click", function() {
			leftSel.find("option:selected").each(function() {
				$(this).remove().appendTo(rightSel);
			});
		});
		//绑定向左的方向建按钮的click事件，当单击按钮时，右侧列表框选中的项会添加到左侧列表框中，完成移除的操作。
		$("#toleft").bind("click", function() {
			rightSel.find("option:selected").each(function() {
				$(this).remove().appendTo(leftSel);
			});
		});
		//完成双击选择事件，当双击该项时，该项立即从该列表框中移除，并添加到与之相对的列表框中。
		leftSel.dblclick(function() {
			$(this).find("option:selected").each(function() {
				$(this).remove().appendTo(rightSel);
			});
		});
		rightSel.dblclick(function() {
			$(this).find("option:selected").each(function() {
				$(this).remove().appendTo(leftSel);
			});
		});
		//将右侧选择框中的项组成一个字符串或数组提交给后台
		$("#saveClick").click(function() {
			var selVal = [];
			rightSel.find("option").each(function() {
				selVal.push(this.value);
			});
			selVals = selVal.join(",");
			if (selVals == "") {
				alert("没有选择任何项！");
			} else {
				//alert(objectId + "_" + selVals);
				save(objectId, selVals);
			}
		});
		
		function save(objectId, relIds) {
			$.post(context_ + "/" + templateUrl + "/saveRels", {
				objectId : objectId,
				relIds : relIds

			}, function(result) {
				if (result.success) {
					$.messager.show({ // show
						// tips
						title : '提示',
						msg : result.msg
					});
				} else {
					$.messager.alert('错误', result.msg);
				}
			}, 'json');
		}
	</script>
</body>
</html>