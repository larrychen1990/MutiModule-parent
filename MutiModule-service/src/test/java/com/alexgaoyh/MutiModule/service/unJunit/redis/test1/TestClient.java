package com.alexgaoyh.MutiModule.service.unJunit.redis.test1;

public class TestClient {

	public static void main(String[] args) {
		//new RedisClient_Jedis().test();
		new RedisClient_ShardedJedis().test();
	}
}
