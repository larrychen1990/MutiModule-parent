package com.alexgaoyh.MutiModule.service.unJunit.redis.test1;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient_ShardedJedis {

	private ShardedJedis shardedJedis;
	
	private ShardedJedisPool sharededJedisPool;
	
	public RedisClient_ShardedJedis() {
		init();
	}
	
	public void init() {
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(10001);
		config.setTestOnBorrow(false);
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.0.137", 6379, "master"));
		
		sharededJedisPool = new ShardedJedisPool(config, shards);
		
		shardedJedis = sharededJedisPool.getResource();
	}
	
	public void test() {
		
		String returnStr = shardedJedis.get("aa");
		System.out.println(returnStr);
		
		//归还 释放
		sharededJedisPool.returnResource(shardedJedis);
		
	}
}

