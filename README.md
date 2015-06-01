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
#20150527
	后台登陆增加filter(登陆与否状态校验)，后台管理页面顶部功能,左边sysmanResource树结构实现，
	RedisClient增加方法（过期时间），重置某个key的过期时间；
	对LoginFilter在登陆状态下重置过期时间，避免登陆后即便操作状态下，缓存也会过期；
	后台管理页面用户是否有操作当前资源的权限判定；
#20150528
	后台登陆页面，验证码和用户登陆信息，修改为使用session机制（原先使用redis，会出现缓存覆盖情况）；
#20150601
	persist层增加逻辑删除sql相关；service层增加逻辑删除相关（通用方法写入模板文件中）
	后台管理页面，逻辑删除功能实现，修复后台用户登陆后，登陆信息session与redis缓存匹配的bug
	以实现RBAC相关单表CRUD,下一步实现rel关系处理