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
		
		function parseDate(date){
			return timeStamp2String(date);
		}
		
		function timeStamp2String(time){  
		    var datetime = new Date();  
		    datetime.setTime(time);  
		    var year = datetime.getFullYear();  
		    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
		    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
		    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
		    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
		    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();  
		    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;  
		} 
</script>