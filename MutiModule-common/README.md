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
	
#20150709
	增加 DesUtilss 类 ，为 对称加密解密类，其中引入自定义的 BASE64DecoderReplace 类，用来提到(sun.misc.BASE64Decoder类)
	对称加密解密算法，可以用来在CookieUtilss 方法中，对存入的cookie值进行处理，防止客户端改变cookie值进行非法操作。
	
#20150711
	扩展  mybatis-generator-maven-plugin 功能，在自动生成代码时添加分页功能：
		com.MutiModule.common.mybatis.plugin.PaginationPlugin mysql 分页扩展插件
			com.MutiModule.common.vo.mybatis.pagination.Page 分页插件依赖的分页类 
			
		com.MutiModule.common.mybatis.plugin.DeleteLogicByIdsPlugin 自定义的扩展插件
			实现增加一个方法，方法名称为 deleteLogicByIds， 在 接口文件和XML的sql文件中，都增加自定义的方法。