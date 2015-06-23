#20150623
	打包war项目
	ueditor部分的demo测试web项目，依赖于MutiModule-ueditorClass MutiModule-ueditor 两个项目
	MutiModule-ueditorClass 部分 ，使用 dependency 将jar依赖进来
	MutiModule-ueditor 部分，使用  maven-war-plugin 插件，将其部分的ueditor相关的js css image 部分依赖进来；
	
	添加 tomcat7-maven-plugin 插件功能，便于部署测试
	相关配置项 参见 http://my.oschina.net/alexgaoyh/blog/398869
	完成ueditor的功能抽离和demo书写，并且使用tomcat7插件进行功能测试，整体测试完成并通过
	注意： tomcat7:deploy  tomcat7:redeploy  tomcat7:undeploy 命令的使用