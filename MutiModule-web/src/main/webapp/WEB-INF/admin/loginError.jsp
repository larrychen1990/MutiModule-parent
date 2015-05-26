<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Error</title>
</head>
	<body>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.1.js"></script>
		Login Error...${mapInfo.errorMsg}
	</body>
	
	<script type="text/javascript">
		
		var context_ = '${context_}';
		
		$(document).ready(function(){
			window.setTimeout("window.location='"+context_+"/admin/sysmanUser/login'",1000); 
		});
	</script>
</html>