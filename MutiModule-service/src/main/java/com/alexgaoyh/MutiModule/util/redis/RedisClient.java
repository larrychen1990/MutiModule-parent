package com.alexgaoyh.MutiModule.util.redis;

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisClient 客户端功能
 * @author alexgaoyh
 *
 */
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
	
	/**
	 * 从redis中获取信息
	 * @param key 键
	 * @return 值
	 */
	public static String get(String key) {
		
		Jedis jedis = jedisPool.getResource();
		
		String returnStr = jedis.get(key);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
		return returnStr;
	}
	
	/**
	 * 将值value放入键key的redis缓存中
	 * @param key	键
	 * @param value 值
	 * @return
	 */
	public static String add(String key, String value) {
		
		Jedis jedis = jedisPool.getResource();
		
		String returnStr = jedis.set(key, value);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
		return returnStr;
	}
	
	/**
	 * 将值value放入键key的redis缓存中 有效期为 second
	 * @param key	键
	 * @param value 值
	 * @param seconds	有效期
	 * @return
	 */
	public static String add(String key, String value, int seconds) {
		Jedis jedis = jedisPool.getResource();
		
		String returnStr = jedis.setex(key, seconds, value);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
		return returnStr;
	}
	
	/**
	 * 删除缓存
	 * @param key
	 * @return
	 */
	public static Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		
		Long returnLong = jedis.del(key);
		
		//归还 释放
		jedisPool.returnResource(jedis);
		
		return returnLong;
	}
}
