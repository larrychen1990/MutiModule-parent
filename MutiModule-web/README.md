updated:

#20150225
	增加后台用户登陆页面；
	
#20150526
	后台登陆页面增加登陆校验功能，下一步对/admin下的所有请求，增加listener监听，避免未登录进行访问；
	
#20150527
	后台登陆增加filter(登陆与否状态校验)，后台管理页面顶部功能,左边sysmanResource树结构实现；
	对LoginFilter在登陆状态下重置过期时间，避免登陆后即便操作状态下，缓存也会过期；
	后台管理页面用户是否有操作当前资源的权限判定；
	
#20150528
	后台登陆页面，验证码和用户登陆信息，修改为使用session机制；
	
#20150601
	后台管理页面，增加/修改/删除 功能实现，修复后台用户登陆后，登陆信息session与redis缓存匹配的bug
	以实现RBAC相关单表CRUD,下一步实现rel关系处理
	
#20150602
	后台管理页面，rel关系处理(sysmanUser-sysmanRole-sysmanResource 部分)，
	jquery左右选择 easyui樹形控件
	
#20150701
	登陸驗證去除session部分，改為使用cookie部分
	同時去除驗證碼部分	
	
#20150713
	insertRel 关系测试，同时生成实体信息A B，并且将返回的主键id进行关联表数据插入
		Demo demo = new Demo();
		demo.setDeleteFlag(0);
		demo.setCreateTime(new Date());
		demo.setName("demo/index");
		demoService.insert(demo);
		
		Demo demo2 = new Demo();
		demo2.setDeleteFlag(0);
		demo2.setCreateTime(new Date());
		demo2.setName(demo.getId() + "");
		demoService.insert(demo2);
