MutiModule-persist部分：
	1、首先创建对应对应的数据库表结构，之后修改 genetatorConfig.xml 部分，targetPackage对应的包路径，tableName对应的数据库表名称，项目（MutiModule-persist）右键，
	run As 选择 mybatis-generator:generate，执行完毕后，生成对应的 模板文件；
	
	2、在模板文件中添加对应的分页支持：
		2.1:  *Example.java 文件增加  protected MyRowBounds myRowBounds(get set方法省略)；
			序列化相关操作 implements Serializable
		
		2.2:  *Mapper.xml 文件增加如下部分， 
				<sql id="myRowBoundsSQL">
					<if test="myRowBounds != null">
						limit ${myRowBounds.offset}, ${myRowBounds.limit}
					</if>
				</sql>
				
			并将这个  myRowBoundsSQL 添加到 selectByExample包含的select语句中
				<!-- alexgaoyh begin -->
				<include refid="myRowBoundsSQL" />
			    <!-- alexgaoyh end -->
						
		2.3:
			/**  20150608 removed 接下来的bean定义交由MapperScannerConfigurer代替，较少代码段书写
				使用MapperScannerConfigurer代替一个个的bean定义
			module-persist-bean.xml 里面增加对应的bean 设定
				<bean id="*Mapper" class="org.mybatis.spring.mapper.MapperFactoryBean">  
			        <property name="sqlSessionFactory" ref="sqlSessionFactory" />  
			        <property name="mapperInterface" value="com.alexgaoyh.MutiModule.persist.*.*Mapper" />  
				</bean>
			*/
			
			
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

	3： 树形结构
		3.1 *。mapper.xml里面，数据库查询如下所示：
			<!-- 树形结构查询  alexgaoyh begin -->
			<resultMap type="com.alexgaoyh.MutiModule.persist.util.TreeNode" id="treeNodeResultMap">  
		        <id property="id" column="id"/>  
		        <result property="text" column="text"/>  
		        <result property="isLeaf" column="isLeaf"/>  
		        <result property="iconCls" column="iconCls"/>  
		        <result property="state" column="state"/>  
		        <!-- 查询父模块 -->  
		        <association property="parent" column="parentId" select="getTreeNodeById" />  
		        <!-- 查询子模块 -->  
		        <collection property="children" column="id" select="getChildrenTreeNode" />  
		    </resultMap>  
		      
		    <select id="selectTreeNodeBySysmanResourceId" parameterType="int" resultMap="treeNodeResultMap">  
		        select * from sysmanresource where id = #{id};
		    </select>  
		      
		    <select id="getTreeNodeById" parameterType="int" resultMap="treeNodeResultMap">  
		        select * from sysmanresource where id = #{id};
		    </select>  
		      
		    <select id="getChildrenTreeNode" parameterType="int" resultMap="treeNodeResultMap">  
		        select * from sysmanresource where parentId = #{id};
		    </select>
		    <!-- 树形结构查询  alexgaoyh end -->			
		3.2 *Mapper.java文件里面，相关查询如下所示：
				//id为需要根据哪个节点开始，对此节点下的数据进行树形结构转换，测试时可以递归操作，对树形结构进行深度优先遍历。
				//方法名称对应xml文件里面的select语句部分
				List<TreeNode> selectTreeNodeBySysmanResourceId(Integer id);
				
	4: 删除操作，本例的删除只是逻辑删除(deleteFlag)状态改变
		4.1	*。Mapper.java 类增加
				public int deleteLogicByIds(@Param("deleteflag")Integer deleteFlag, @Param("ids")Integer[] ids);
		4.2 *。Mpaaer.xml 增加
				<update id="deleteLogicByIds">
					update sysmanuser
					set deleteFlag = #{deleteflag,jdbcType=INTEGER}
					where id in
					<foreach item="item" index="index" collection="ids" open="(" separator="," close=")">
				            #{item}
				        </foreach>
				  </update>
		4.3 增加测试方法，返回更新状态的数据
	5： sysmanRole类，重写父类方法 hashCode() equals()
		保证在对形如集合处理操作的时候，保证id相同的话，两个对象实际是相同的，例如 List.removeALL(?) 
		
	6： 使用MapperScannerConfigurer 代替一个个的bean定义。
							