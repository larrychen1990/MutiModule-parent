maven多模块项目管理，整合spring mybatis，多模块划分： web层，service层， persist层， 其他（capthca验证码）；
已经实现实现分页功能，事务支持，多模块功能

#upgrade更新部分：

#20150504
	增加模板类文件：
		persist持久化部分使用mybatis，使用maven-mybatis-generator插件，生成对应的模板文件，并添加分页操作
		service部分，自己书写fpl，使用freemarker生成service业务层代码；
#20150509
	增加针对updateByPrimaryKeySelective操作的缓存更新功能 AOP
#20150523
	persist service层增加RBAC功能权限相关处理，并对结果集进行树形结构转换功能。
#20150225
	增加后台用户登陆页面；
#20150526
	后台登陆页面增加登陆校验功能，下一步对/admin下的所有请求，增加listener监听，避免未登录进行访问；