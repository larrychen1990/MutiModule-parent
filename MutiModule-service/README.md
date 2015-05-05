MutiModule-service 部分：
	1: 代码生成器
		1.1:service 部分依赖于 persist 部分，在persist 部分中使用 mybatis-generator 生成相关模板文件之后，在 serivce 子模块中  
			Run As CodeGeneratorUtil 类，使用 freemarker 生成相关的类文件，具体的多个service部分方法可以根据需要对方法进行增加处理。
			PS: 这里执行run as方法之后，package部分会报错，使用错误提示更新一下即可（move package）。后期可以根据需要，对模板文件的具体方法进行修改维护操作 。
		
		1.2: 在 module-service-config.xml 文件中，增加新的bean
		
		1.3： 书写junit测试用例，并且执行相关操作进行测试
		
	2： service 数据库层外面增加redis 缓存层，并且增加单元测试，实现方法为使用spring AOP ，在对应的 匹配规则下，增加相关操作
		2.1 ： 测试方法为 selectByPrimaryKey(key)， 第一次执行过程中，从缓存中没有取到值，那么执行 pjp.proceed(); 方法，并将返回值放到缓存中，
			第二次执行这个方法的时候，缓存中存在值，那么便直接返回返回这个缓存中的存在值。
			
	3:
		3.1: RedisAdvice 类 的 doAround() 方法
			3.1.1： 需要获取到请求的是哪个类，因为需要根据这个请求的类，再拼接上请求的参数，封装key 进行缓存数据的存取操作
						pjp.toShortString()  输出    execution(DemoServiceImpl.insert(..))
						args[0] 为发送的key 请求 
					这样，就可以根据这两个参数进行key部分的拼接；
			3.1.2： 因为此时 set/get 方法 是将 value 进行json操作的,那么在进行从缓存中取数据的时候，需要将json串转换为对应的 实体 类型
					pjp.getTarget().getClass().getDeclaredMethod(pjp.getSignature().getName(),
        				((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes()).getReturnType()
        			上面这个方法，就能够够获取到 需要返回的数据类型    
        			PS: 如果这个请求是  com.alexgaoyh.MutiModule.service.demo.impl.selectByPrimaryKey(key) 发送上的请求，
        				那么上面这个方法返回的就是这个方法的返回值 ， 即  Demo 对应的Class<T>