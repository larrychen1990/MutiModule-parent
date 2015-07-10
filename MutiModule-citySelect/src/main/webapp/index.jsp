<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<!-- 省市区 联动 -->
	<link rel="stylesheet" type="text/css" href="css/wi-areaselect.css">
	<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
	<script type="text/javascript" charset="utf-8" src="js/jquery-v1.11.3.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/jquery-ui.js"></script>
</head>
<body>
<div style="width: 300px;height: 63px;margin-left: 25px;margin-top: 30px;" class="locationRange">
	<div class="search-div1" >
		 <input id="source-area" name="condition.startAddr" class="area-t hy-select" autocomplete="off" style="margin-left:5px;width: 318px;" placeholder="城市名(中文/拼音)"/>
		 <div id="source-area-select" class="area-select"></div>
	</div>
	<br></br><br></br>
	<div class="search-div1" >
		 <input id="dest-area" name="condition.endAddr" class="area-t hy-select" autocomplete="off" style="margin-left:5px;width: 318px;" placeholder="城市名(中文/拼音)"/>
		 <div id="dest-area-select" class="area-select"></div>
	</div>
	<br></br><br></br>
	<button onclick="getCode();">getCode</button><button onclick="getText();">getText</button>
	<button onclick="setText();">set</button>
</div>
	<script type="text/javascript">
		function getCode() {
			alert($("#source-area").attr("code"));
		}
		
		function getText() {
			alert($("#source-area").val());
		}
		
		function setText() {
			$("#source-area").attr("code", "1-5025-37");
			$("#source-area").val("北京-北京市-东城区");
		}
	
	</script>
	<script type="text/javascript" charset="utf-8" src="js/areaData.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/areaselect.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/express.js"></script>
</body>
</html>
