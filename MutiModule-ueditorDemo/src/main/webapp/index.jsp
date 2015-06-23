<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/jquery/jquery-v1.11.3.js"></script>
	
</head>
<body>
	<script id="content" name="content" type="text/plain" style="width:1024px;height:500px;"></script>
	
	
	<script type="text/javascript">
		var context_ = "${pageContext.request.contextPath}";
	</script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
    	$( function() {
			UE.getEditor('content');
		});
    </script>
</body>
</html>