<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/index.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.4.1.js"></script>
</head>
<body>

	<form id="login" action="${pageContext.request.contextPath}/admin/sysmanUser/doLogin" method="post">
		<h1>Log In</h1>
		<fieldset id="inputs">
			<input name="userName" id="userName" type="text" placeholder="Username" autofocus required> 
			<input name="passWord" id="passWord"  type="password" placeholder="Password" required>
		</fieldset>
		<input type="checkbox" name="rememberMe" value="true" /> Remember me
		<fieldset id="actions">
			<input type="submit" id="submit" value="Log in"> 
		</fieldset>
	</form>

	
	<script type="text/javascript">
		var context_ = '${context_}';
		
		var userName = '${userName}';
		if(userName != null || userName != ""){
			$("#userName").val(userName);
		}
	
	</script>

</body>
</html>
