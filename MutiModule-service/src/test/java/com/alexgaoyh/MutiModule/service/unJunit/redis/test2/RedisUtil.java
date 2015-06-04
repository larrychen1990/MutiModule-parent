package com.alexgaoyh.MutiModule.service.unJunit.redis.test2;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis������,���ڻ�ȡRedisPool.
 */
public class RedisUtil  {
	/**
	 * ˽�й�����.
	 */
	private RedisUtil() {
	    
	}
	
	/**
     *�༶���ڲ��࣬Ҳ���Ǿ�̬�ĳ�Աʽ�ڲ��࣬���ڲ����ʵ�����ⲿ���ʵ��
     *û�а󶨹�ϵ������ֻ�б����õ�ʱ�Ż�װ�أ��Ӷ�ʵ�����ӳټ��ء�
     */
    private static class RedisUtilHolder{
        /**
         * ��̬��ʼ��������JVM����֤�̰߳�ȫ
         */
        private static RedisUtil instance = new RedisUtil();
    }

    /**
     *��getInstance������һ�α����õ�ʱ������һ�ζ�ȡ
     *RedisUtilHolder.instance������RedisUtilHolder��õ���ʼ�������������װ�ز�����ʼ����ʱ�򣬻��ʼ�����ľ�
     *̬�򣬴Ӷ�����RedisUtil��ʵ���������Ǿ�̬�������ֻ���������װ�����ʱ���ʼ��һ�Σ��������������֤�����̰߳�ȫ�ԡ�
     *���ģʽ���������ڣ�getInstance������û�б�ͬ��������ֻ��ִ��һ����ķ��ʣ�����ӳٳ�ʼ����û�������κη��ʳɱ���
     */
	public static RedisUtil getInstance() {
		return RedisUtilHolder.instance;
	}
	
	/**
	 * ����ģʽ�����ҽ����е�JedisPool�����뵽maps�����У�key---ip:port�� value---JedisPool
	 */
	private static Map<String,JedisPool> maps  = new HashMap<String,JedisPool>();
    
    /**
     * ��ȡ���ӳ�.
     * @return ���ӳ�ʵ��
     */
    private static JedisPool getPool(String ip,int port) {
    	String key = ip+":" +port;
    	JedisPool pool = null;
        if(!maps.containsKey(key)) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(5);
    		config.setMaxWaitMillis(10001);
    		config.setTestOnBorrow(false);
            try{  
                /**
                 *��������� java.net.SocketTimeoutException: Read timed out exception���쳣��Ϣ
                 *�볢���ڹ���JedisPool��ʱ�������Լ��ĳ�ʱֵ. JedisPoolĬ�ϵĳ�ʱʱ����2��(��λ����)
                 */
                pool = new JedisPool(config, ip, port,2000);
                maps.put(key, pool);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }else{
        	pool = maps.get(key);
        }
        return pool;
    }

	/**
	 * ��ȡRedisʵ��.
	 * @return Redis������ʵ��
	 */
	public static Jedis getJedis(String ip,int port) {
		Jedis jedis  = null;
		
		try{ 
			jedis = getPool(ip,port).getResource();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// ���ٶ���  
			getPool(ip,port).returnBrokenResource(jedis);  
		}
		
		return jedis;
	}

	/**
	 * �ͷ�redisʵ�������ӳ�.
     * @param jedis redisʵ��
     */
	public static void closeJedis(Jedis jedis,String ip,int port) {
		if(jedis != null) {
		    getPool(ip,port).returnResource(jedis);
		}
	}
}
