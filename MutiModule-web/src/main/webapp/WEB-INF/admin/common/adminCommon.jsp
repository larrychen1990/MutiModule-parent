<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-extend/easyui_dataGrid_extend.js"></script>

<script type="text/javascript">
		var context_ = '${context_}';
		var templateUrl = '${moduleName}';
</script>