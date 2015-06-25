package com.alexgaoyh.MutiModule.service.unJunit.redis.test2.pojo;

import java.util.Date;

import redis.clients.jedis.Jedis;

import com.alexgaoyh.MutiModule.service.unJunit.redis.test2.RedisUtil;

public class SerializablePojoRedisTest {

	private static String IP = "192.168.1.157";
	private static Integer PORT = 6379;


	public static void main(String[] args) {
		//testSerializablePojo() ;
		testUnSerializeFromReids() ;
	}

	/**
	 * 测试Redis存入Student对象（对象转换为二进制数据）,此Student对象并不添加addedColumn的属性，
	 */
	public static void testSerializablePojo() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		try {
			Student stu = new Student();
			stu.setAge(1);
			stu.setBirthDay(new Date());
			stu.setName("alexgaoyh");
			stu.setSchoolName("alexgaoyhSchoolName");
			
			jedis.set("stu".getBytes(), SerializeUtil.serialize(stu));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}

	}
	
	/**
	 * 修改Student对象，增加addedColumn属性，并且将对象从缓存中取出，并且输出相关的数据，看看是否正常
	 */
	public static void testUnSerializeFromReids() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		try {
			Student stu = null;
			byte[] byteobj = jedis.get("stu".getBytes());
			Object obj = SerializeUtil.unserialize(byteobj);
			if(obj != null) {
				stu = (Student)obj;
				System.out.println(stu.getAddedColumn());
				System.out.println(stu.getName());
				System.out.println(stu.getSchoolName());
				System.out.println(stu.getAge());
				System.out.println(stu.getBirthDay());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}
}
