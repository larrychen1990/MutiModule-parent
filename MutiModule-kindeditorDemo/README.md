#20150610
	实现与  MutiModule-ueditorDemo 相似的功能；
	添加新的富文本编辑器的功能  kindeditor  https://github.com/kindsoft/kindeditor 
	
#20150704
	删除 MutiModule-kindeditorClass 模块，将相关的类文件移动到 MutiModule-common 模块中
	讲原先MutiModule-upload 模块删除，其中文件上传部分js jsp文件移动到 MutiModule-kindeditorDemo 内部
	其中jsUpload.jsp 中对应的文件上传URL 采用 kindeditor 部分，形成统一的文件上传方法。
	
#20150706
	此模块增加图片处理相关，ImgServlet.java 文件
	形如通过 kindeditor 进行图片上传之后，返回的图片链接为 ：
	http://127.0.0.1:8080/MutiModule-kindeditorDemo/attached/image/20150706/20150706122314_613.jpg
	此时可以在文件后缀后面增加形如 !100_100 的方式，表示对原图片进行 宽100 长100 的处理
	
	如果浏览器进行 http://127.0.0.1:8080/MutiModule-kindeditorDemo/attached/image/20150706/20150706122314_613!200_200.jpg
	的访问，那么，将会对 20150706122314_613.jpg 原图进行 宽:高 =200:200 的处理，并且返回到浏览器
	
#20150707
	移除kindeditorDemo模块内所有资源文件，确保此模块仅仅为DEMO测试部分，不包含任何资源文件
	将此模块下的js文件移动至 MutiModule-kindeditor 模块内部