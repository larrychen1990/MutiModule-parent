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
</head>
<body>
	<div id="toolbar-1">
		<a href="javascript:history.go(-1);" class="easyui-linkbutton" iconCls="icon-back" plain="true">返回</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save" plain="true" id="saveRoleResourceRelClick">保存</a>
	</div>
	<div><p>当前角色信息： 编号：${object.id }; 名稱： ${object.name }</p></div>
	
	<div class="easyui-panel" style="padding:5px">
        <ul id="tt" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/${moduleName}/getMenus?id=${object.id }',method:'get',animate:true,checkbox:true"></ul>
    </div>
    
    <script type="text/javascript">
    	var objectId = ${object.id};
    
    	$("#saveRoleResourceRelClick").click(function() {
            var nodes = $('#tt').tree('getChecked');
            var s = '';
            for(var i=0; i<nodes.length; i++){
                if (s != '') s += ',';
                s += nodes[i].id;
            }
            //alert(s);
            save(objectId, s);
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