package com.alexgaoyh.MutiModule.service.unJunit.redis.test1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient_Jedis {
	
	//客户端连接
	private Jedis jedis;
	//连接池
	private JedisPool jedisPool;
	
	/**
	 * 构造函数
	 */
	public RedisClient_Jedis() {
		//调用初始化方法
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(10001);
		config.setTestOnBorrow(false);
		
		jedisPool = new JedisPool(config, "192.168.0.137", 6379);
		
		jedis = jedisPool.getResource();
	}
	
	public void test() {
		
		String returnStr = jedis.get("aa");
		System.out.println(returnStr);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
	}


}
