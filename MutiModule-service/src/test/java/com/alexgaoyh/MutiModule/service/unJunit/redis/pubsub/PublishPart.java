package com.alexgaoyh.MutiModule.service.unJunit.redis.pubsub;

import redis.clients.jedis.Jedis;

/**
 * 实现发布端代码 发布消息只用调用Jedis的publish(...)方法即可。
 */
public class PublishPart {
	
	private static String IP = "192.168.1.157";
	private static Integer PORT = 6379;


	public static void main(String[] args) {
		publish();
	}
	
	public static void publish() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		try {
			jedis.publish("hello_1", "alexgaoyh_publish_1");  
			jedis.publish("hello_2", "alexgaoyh_publish_2");  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}

}

