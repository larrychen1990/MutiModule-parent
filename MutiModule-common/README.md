#20150701
	增加 MutiModule-common 部分，通用模块，util方法……
	PropertiesUtilss	配置文件讀取util
	
#20150704
	kindeditor 4.1.11 版本；
	将源码包的文件上传和文件空间两个对应的jsp文件改为servlet部分；
		对其中 FileUploadServlet。java 部分进行部分修改，增加 if(item.getName() != null && !item.getName().equals("")){} 部分判断，用来判断是否选择了文件
	
#20150706
	GraphicsMagick+im4java 进行图片处理操作， 需要注意的是，在windows测试过程中，需要修改 imageMagicPath.properties 文件中 imageMagicPath 的制定路径
	linux 环境中，直接打包就可以，不需要修改相关。
	不管是什么操作系统，图片处理功能，都需要安装  ImageMagick http://www.graphicsmagick.org/index.html 
	