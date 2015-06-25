package com.alexgaoyh.MutiModule.service.unJunit.redis.pubsub;

import redis.clients.jedis.Jedis;

/**
 * Jedis有两种订阅模式：
 * subsribe(一般模式设置频道)和psubsribe(使用模式匹配来设置频道)。
 * 不管是那种模式都可以设置个数不定的频道。订阅得到信息在将会lister的onMessage(...)方法或者onPMessage(...)中进行进行处理，这里我们只是做了简单的输出。
 */
public class SubscribePart {
	
	private static String IP = "192.168.1.157";
	private static Integer PORT = 6379;


	public static void main(String[] args) {
		subscribe();
	}
	
	public static void subscribe() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		try {
			
			final PubSubListener listener = new PubSubListener();  
			jedis.psubscribe(listener, new String[]{"hello_*"});//使用模式匹配的方式设置频道 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}


}

