#20150710
	省市区地址选择框，非sleect选择框， javaScript jQuery-ui(autoComplete)
	弹出层完成省市区地址选择框，并且支持键盘输入字符完成地区选中；
	
	jquery-ui.css 搜索 .ui-autocomplete 这个css类的定义
		修改为
		.ui-autocomplete {	
			position: absolute;	
			top: 0;	
			left: 0;	
			cursor: default;	
			overflow-y:scroll;	
			overflow-x:hidden; 	
			max-height: 200px; 
		}

	对于低版本的IE，可能会不识别max-height
	那就在上述代码后加上 就可以实现了
 _		height:expression(this.scrollHeight >200 ? "200px" : "auto");
