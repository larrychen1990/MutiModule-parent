此模块为logback的日志模块，用来记录相关日志信息
	具体日志信息记录方式在  logback.xml 文件中
	APPTest方法中，有测试用例，记录相关的日志信息

使用此模块的方式为：
	pom.xml 添加此模块的依赖关系
		<dependency>
			<groupId>com.alexgaoyh</groupId>
			<artifactId>MutiModule-logback</artifactId>
			<version>${project.version}</version>
		</dependency>
		
	类文件中增加 
		Logger logger = LoggerFactory.getLogger(XXXXXX.class);
		
		方法中使用如下方法来增加日志记录的信息
			logger.debug("XXXXXX");
			logger.info("XXXXXX");
			logger.warn("XXXXXX");
			logger.error("XXXXXX");