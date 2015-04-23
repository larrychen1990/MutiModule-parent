<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<head></head>
<body>
	<c:forEach var="data" items="${pagination.data}" varStatus="status">
	${data.id }
	</c:forEach>
</body>
</html>