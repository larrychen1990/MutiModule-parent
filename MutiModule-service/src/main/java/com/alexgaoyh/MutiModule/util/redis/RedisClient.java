package com.alexgaoyh.MutiModule.util.redis;

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	
	//连接池
	private static JedisPool jedisPool;
	
	/**
	 * 初始化
	 */
	static{
		
		Properties pro = new Properties();
		try {
			
			pro.load(RedisClient.class.getResourceAsStream("/redis-config.properties"));
			
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(Integer.parseInt(pro.getProperty("redis.maxIdle")));
			config.setMaxWaitMillis(Integer.parseInt(pro.getProperty("redis.maxWaitMillis")));
			config.setTestOnBorrow(Boolean.parseBoolean(pro.getProperty("redis.testOnBorrow")));
			
			jedisPool = new JedisPool(config, pro.getProperty("redis.hostName"), Integer.parseInt(pro.getProperty("redis.port")));
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	
	public static String get(String key) {
		
		Jedis jedis = jedisPool.getResource();
		
		String returnStr = jedis.get(key);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
		return returnStr;
	}
	
	public static String add(String key, String value) {
		
		Jedis jedis = jedisPool.getResource();
		
		String returnStr = jedis.set(key, value);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
		return returnStr;
	}
}
