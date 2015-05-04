MutiModule-service 部分：
	1:service 部分依赖于 persist 部分，在persist 部分中使用 mybatis-generator 生成相关模板文件之后，在 serivce 子模块中  
		Run As CodeGeneratorUtil 类，使用 freemarker 生成相关的类文件，具体的多个service部分方法可以根据需要对方法进行增加处理。
		PS: 这里执行run as方法之后，package部分会报错，使用错误提示更新一下即可（move package）。后期可以根据需要，对模板文件的具体方法进行修改维护操作 。
	
	2: 在 module-service-config.xml 文件中，增加新的bean
	
	3： 书写junit测试用例，并且执行相关操作进行测试