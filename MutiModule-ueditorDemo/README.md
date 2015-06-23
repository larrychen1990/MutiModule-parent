#20150623
	打包war项目
	ueditor部分的demo测试web项目，依赖于MutiModule-ueditorClass MutiModule-ueditor 两个项目
	MutiModule-ueditorClass 部分 ，使用 dependency 将jar依赖进来
	MutiModule-ueditor 部分，使用  maven-war-plugin 插件，将其部分的ueditor相关的js css image 部分依赖进来；