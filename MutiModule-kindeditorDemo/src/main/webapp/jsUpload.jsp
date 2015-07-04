<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<!-- 引入相关的js文件，相对路径  -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-v1.11.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fileUpload//ajaxfileupload.js"></script>

<!-- 执行上传文件操作的函数 -->
<script type="text/javascript">
	function ajaxFileUpload() {
		$.ajaxFileUpload({
			url : 'fileUpload', //需要链接到服务器地址
			secureuri : false,
			fileElementId : 'fileToUpload', //文件选择框的id属性
			dataType : 'json', //服务器返回的格式，可以是json, xml
			success : function(data, status){ //相当于java中try语句块的用法
				$('#result').html('添加(1:失败；0:成功):' + data.error + '------' + (data.error == 1 ? data.message : data.url));
			},
			error : function(data, status, e){ //相当于java中catch语句块的用法
				$('#result').html('添加(1:失败；0:成功):' + '------' + data.message);
			}
		});

	}
</script>
</head>

<body>
	<div>
		<input type="file" id="fileToUpload" name=fileToUpload /> <input
			type="button" value="提交" onclick="ajaxFileUpload()" />
	</div>
	<div id="result"></div>

</body>
</html>