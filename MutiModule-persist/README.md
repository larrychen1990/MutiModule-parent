MutiModule-persist部分：
	1、首先创建对应对应的数据库表结构，之后修改 genetatorConfig.xml 部分，targetPackage对应的包路径，tableName对应的数据库表名称，项目（MutiModule-persist）右键，
	run As 选择 mybatis-generator:generate，执行完毕后，生成对应的 模板文件；
	
	2、在模板文件中添加对应的分页支持：
		2.1:  *Example.java 文件增加  protected MyRowBounds myRowBounds(get set方法省略)；
		
		2.2:  *Mapper.xml 文件增加如下部分， 并将这个  myRowBoundsSQL 添加到 selectByExample包含的select语句中
				<sql id="myRowBoundsSQL">
					<if test="myRowBounds != null">
						limit ${myRowBounds.offset}, ${myRowBounds.limit}
					</if>
				</sql>
			
		2.3:  module-persist-bean.xml 里面增加对应的bean 设定
				<bean id="*Mapper" class="org.mybatis.spring.mapper.MapperFactoryBean">  
			        <property name="sqlSessionFactory" ref="sqlSessionFactory" />  
			        <property name="mapperInterface" value="com.alexgaoyh.MutiModule.persist.*.*Mapper" />  
				</bean>
			
		2.3： 进行junti测试 *MyBatisTest.java
				//定义查询过滤类
				DemoExample demoExample = new DemoExample();
				demoExample.setOrderByClause("id desc");
				
				//定义分页相关，第一页，一页10条
				MyRowBounds myRowBounds0 = new MyRowBounds(1,10);
				demoExample.setMyRowBounds(myRowBounds0);
				//查询总记录数，list集合
				int count0 = demoMapper.countByExample(demoExample);
				List<Demo> demoList0 = demoMapper.selectByExample(demoExample);
			
			