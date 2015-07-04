#20150610
	实现与  MutiModule-ueditorDemo 相似的功能；
	添加新的富文本编辑器的功能  kindeditor  https://github.com/kindsoft/kindeditor 
	
#20150704
	删除 MutiModule-kindeditorClass 模块，将相关的类文件移动到 MutiModule-common 模块中
	讲原先MutiModule-upload 模块删除，其中文件上传部分js jsp文件移动到 MutiModule-kindeditorDemo 内部
	其中jsUpload.jsp 中对应的文件上传URL 采用 kindeditor 部分，形成统一的文件上传方法。