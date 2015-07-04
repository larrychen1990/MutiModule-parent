maven多模块项目管理，整合spring mybatis，多模块划分： web层，service层， persist层， 其他（capthca验证码）；
已经实现后台管理页面RBAC相关权限控制，整合easyui，完成相关CRUD功能。

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
#20150602
	后台管理页面，rel关系处理(sysmanUser-sysmanRole-sysmanResource 部分)，
	jquery左右选择 easyui樹形控件
********************************至此完成RBAC相關權限控制部分***************************************************	

#20150608
	persist层，原先使用形如(如下)所示的代码段来定义一个个的bean，现改为MapperScannerConfigurer(查 找 类 路 径 下 的 映 射 器 并 自 动 将 它 们 创 建 成 MapperFactoryBean)
	<bean id="demoMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">  
        <property name="sqlSessionFactory" ref="sqlSessionFactory" />  
        <property name="mapperInterface" value="com.alexgaoyh.MutiModule.persist.demo.DemoMapper" />  
	</bean>
	
	service层，原先使用入刑（如下）所示的代码段来定义一个个的bean，现改为<context:annotation-config /><context:component-scan base-package="X.X.X" />
	<bean id="demoService" class="com.alexgaoyh.MutiModule.service.demo.impl.DemoServiceImpl">
		<property name="demoMapper" ref="demoMapper" />
	</bean>	  
	
#20150623
	增加webapp项目: 
		MutiModule-ueditorDemo(ueditor部分的demo用例);
		MutiModule-ueditor	  (ueditor部分的js/css/image……部分资源文件)；
	增加quickstart项目：
		MutiModule-uedirotClass（ueditor部分的相关java类文件和添加的servlet部分。ueditor源码类文件部分有部分修改）；
		
	前台有多个web子项目，每个子项目有时候都会依赖于ueditor部分，这样，重复性的东西很多，这样，可以吧这一部分的资源抽离出来（MutiModule-class  MutiModule-ueditor），
	这样的话，java类文件放到MutiModule-class的jar包里面，资源文件js/css/image放到MutiModule-ueditor的war包里面；
	MutiModule-uedirotDemo就是一个测试用例，测试可用。
	
#20150624
	增加 MutiModule-logback 模块，用来统一处理日志记录模块
	使用方法详见  MutiModule-logback/README.md 文件中
	
#20150625
	MutiModule-service层，Redis相关
	SerializablePojoRedisTest 测试类，Redis相关，存入value为序列化之后的Student对象，此后如果Student类属性变更，从缓存中获取value值之后进行反序列化Student对象，验证可用性；

#20150701
	增加MutiModule-upload MutiModule-common 相關
	MutiModule-upload : ajaxfileupload.js 文件上傳相關功能測試完成
	MutiModule-common : 通用方法處理相關
	MutiModule-web : 登陸驗證去除session部分，改為使用cookie部分;	同時去除驗證碼部分	
	
#20150704
	MutiModule-upload　部分。将FileUploadServlet 部分移动到MutiModule-common 