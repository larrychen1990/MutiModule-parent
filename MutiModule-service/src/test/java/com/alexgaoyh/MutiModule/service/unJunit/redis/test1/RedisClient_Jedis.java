package com.alexgaoyh.MutiModule.service.unJunit.redis.test1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient_Jedis {
	
	//�ͻ�������
	private Jedis jedis;
	//���ӳ�
	private JedisPool jedisPool;
	
	/**
	 * ���캯��
	 */
	public RedisClient_Jedis() {
		//���ó�ʼ������
		init();
	}
	
	/**
	 * ��ʼ��
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
		
		//�黹 �ͷ�
		jedisPool.returnResource(jedis);
		
	}


}
