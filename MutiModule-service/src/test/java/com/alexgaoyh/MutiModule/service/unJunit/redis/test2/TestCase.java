package com.alexgaoyh.MutiModule.service.unJunit.redis.test2;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;


/**
 * �ڲ�ͬ���߳���ʹ����ͬ��Jedisʵ���ᷢ����ֵĴ��󡣵��Ǵ���̫���ʵ��Ҳ������Ϊ����ζ�ŻὨ���ܶ�sokcet���ӣ�
 * Ҳ�ᵼ����ֵĴ���������һJedisʵ�������̰߳�ȫ�ġ�Ϊ�˱�����Щ���⣬����ʹ��JedisPool,
 * JedisPool��һ���̰߳�ȫ���������ӳء�������JedisPool����һЩ�ɿ�Jedisʵ�������Դӳ����õ�Jedis��ʵ����
 * ���ַ�ʽ���Խ����Щ���Ⲣ�һ�ʵ�ָ�Ч������
 */
public class TestCase {
	
	private static String IP = "192.168.0.117";
	private static Integer PORT = 6379;


	public static void main(String[] args) {
		//Hello();
		//testString();
		//testList();
		//testSet();
		//sortedSet();
		//testHsh();
		//testOther();
		//testUnUsePipeline();
		//testUsePipeline();
		//testSort1();
		//testSort2();
		//testSort3();
		//testSort5();
		//testMget();
		//queryPageBy();
		//testListStrUsage();
		testSetUsage();
		//testSortedSetUsage();
	}

	/**
	 * ���ŵ�һ�����м򵥵�set get��������
	 */
	public static void Hello() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		try {
			jedis.set("name", "minxr");
			String ss = jedis.get("name");
			System.out.println(ss);//minxr

			// ��ֱ�ۣ�����map ��jintao append���Ѿ��е�value֮��
			jedis.append("name", "jintao");
			ss = jedis.get("name");
			System.out.println(ss);//minxrjintao

			// 2��ֱ�Ӹ���ԭ��������
			jedis.set("name", "jintao");
			System.out.println(jedis.get("name"));//jintao

			// ɾ��key��Ӧ�ļ�¼
			jedis.del("name");
			System.out.println(jedis.get("name"));// ִ�н����null

			/**
			 * mset�൱�� jedis.set("name","minxr"); jedis.set("jarorwar","aaa");
			 */
			jedis.mset("name", "minxr", "jarorwar", "aaa");
			System.out.println(jedis.mget("name", "jarorwar"));//[minxr, aaa]
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}

	}

	/**
	 * ���key�Ƿ����
	 */
	public static void testKey() {
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		System.out.println("=============key==========================");
		// �������
		System.out.println(jedis.flushDB());//ok
		System.out.println(jedis.echo("foo"));//foo
		// �ж�key�����
		System.out.println(jedis.exists("foo"));//false
		jedis.set("key", "values");
		System.out.println(jedis.exists("key"));//true
		
		RedisUtil.closeJedis(jedis,IP,PORT);
	}

	/**
	 * ���value����еĲ��ֲ����� ׷�����ݣ��滻���ݣ�ɾ�����ݣ�
	 */
	public static void testString() {
		System.out.println("==String==");
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		try {
			// �������
			System.out.println(jedis.flushDB());//ok
			// �洢����
			jedis.set("foo", "bar");
			System.out.println(jedis.get("foo"));//bar
			// ��key�����ڣ���洢
			jedis.setnx("foo", "foo not exits");
			System.out.println(jedis.get("foo"));//bar
			
			// ��������
			jedis.set("foo", "foo update");
			System.out.println(jedis.get("foo"));//foo update
			// ׷������
			jedis.append("foo", " hello, world");
			System.out.println(jedis.get("foo"));//foo update hello, world
			
			// ����key����Ч�ڣ����洢����
			jedis.setex("foo", 2, "foo not exits");
			System.out.println(jedis.get("foo"));//foo not exits
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			System.out.println(jedis.get("foo"));//null
			
			
			// ��ȡ����������
			jedis.set("foo", "foo update");
			//������ֵ���ҷ��ؾ�ֵ
			System.out.println(jedis.getSet("foo", "foo modify"));//foo update
			
			// ��ȡvalue��ֵ
			System.out.println(jedis.getrange("foo", 1, 3));//oo
			
			System.out.println(jedis.del(new String[] { "foo", "foo1", "foo3" }));//1
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}

	/**
	 * ���list���Ͻ��еļ򵥲�����
	 * ��key�µ�list���Ͻ��У���ȡ����ֵ�����鳤�ȣ������޸Ĳ���
	 * ����java.util.ArrayList����
	 */
	public static void testList() {
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		try {
			// ��ʼǰ�����Ƴ����е�����
			jedis.del("messages");
			jedis.rpush("messages", "Hello how are you?");
			jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");
			jedis.rpush("messages", "I should look into this NOSQL thing ASAP");

			// ��ȡ����������jedis.lrange�ǰ���Χȡ����
			// ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������
			List<String> values = jedis.lrange("messages", 0, -1);
			System.out.println(values);
			//[Hello how are you?, Fine thanks. I'm having fun with redis., I should look into this NOSQL thing ASAP]
			
			// �������
			System.out.println(jedis.flushDB());//ok
			// �������
			jedis.lpush("lists", "1");
			jedis.lpush("lists", "3");
			jedis.lpush("lists", "2");
			// ���鳤��
			System.out.println(jedis.llen("lists"));//3
			// ����
			System.out.println(jedis.sort("lists"));//[1, 2, 3]
			// �ִ�
			System.out.println(jedis.lrange("lists", 0, 3));//[2, 3, 1]
			// �޸��б��е���ֵ
			jedis.lset("lists", 0, "hello list!");
			// ��ȡ�б�ָ���±��ֵ
			System.out.println(jedis.lindex("lists", 1));//3
			// ɾ���б�ָ���±��ֵ
			System.out.println(jedis.lrem("lists", 1, "1"));//1
			// ɾ���������������
			System.out.println(jedis.ltrim("lists", 0, 1));//ok
			// �б��ջ
			System.out.println(jedis.lpop("lists"));//hello list
			// �����б�ֵ
			System.out.println(jedis.lrange("lists", 0, -1));//[3]

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}

	/**
	 * ���set���Ͻ��м򵥵Ĺ��ܲ���(δ�����set����)
	 * ������ӣ������Ƴ��������Ƿ���ڣ� �����б��ȡ��ɾ��ָ��Ԫ�أ� ���ݳ�ջ�� �����󽻼�/����/�
	 */
	public static void testSet() {
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		try {
			jedis.sadd("myset", "1");
			jedis.sadd("myset", "2");
			jedis.sadd("myset", "3");
			jedis.sadd("myset", "4");
			Set<String> setValues = jedis.smembers("myset");
			System.out.println(setValues);//[3, 2, 1, 4]

			// �Ƴ�noname
			jedis.srem("myset", "4");
			System.out.println(jedis.smembers("myset"));// ��ȡ���м����value//[3, 2, 1]
			
			System.out.println(jedis.sismember("myset", "4"));// �ж� minxr//false // �Ƿ���sname���ϵ�Ԫ��
			
			System.out.println(jedis.scard("sname"));// ���ؼ��ϵ�Ԫ�ظ���//0

			// �������
			System.out.println(jedis.flushDB());//ok
			// �������
			jedis.sadd("sets", "HashSet");
			jedis.sadd("sets", "SortedSet");
			jedis.sadd("sets", "TreeSet");
			// �ж�value�Ƿ����б���
			System.out.println(jedis.sismember("sets", "TreeSet"));//true
			;
			// �����б�ֵ
			System.out.println(jedis.smembers("sets"));//[SortedSet, TreeSet, HashSet]
			// ɾ��ָ��Ԫ��
			System.out.println(jedis.srem("sets", "SortedSet"));//1
			// ��ջ
			System.out.println(jedis.spop("sets"));//HashSet
			System.out.println(jedis.smembers("sets"));//[TreeSet]
			//
			jedis.sadd("sets1", "HashSet1");
			jedis.sadd("sets1", "SortedSet1");
			jedis.sadd("sets1", "TreeSet");
			jedis.sadd("sets2", "HashSet2");
			jedis.sadd("sets2", "SortedSet1");
			jedis.sadd("sets2", "TreeSet1");
			// ����
			System.out.println(jedis.sinter("sets1", "sets2"));//[SortedSet1]
			// ����
			System.out.println(jedis.sunion("sets1", "sets2"));//[HashSet2, HashSet1, TreeSet1, SortedSet1, TreeSet]
			// �
			System.out.println(jedis.sdiff("sets1", "sets2"));//[TreeSet, HashSet1]
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}

	}

	/**
	 * ���set���Ͻ�����ز�����������
	 * ����score��������ݼ��ϵ�����
	 * ���ݻ��ܣ���ȡscore��ֵ��ɾ��Ԫ�أ�������ӵ����ݻ���
	 */
	public static void sortedSet() {
		Jedis redis = RedisUtil.getJedis(IP, PORT);
		try {
			redis.zadd("hackers", 1940, "Alan Kay");
			redis.zadd("hackers", 1953, "Richard Stallman");
			redis.zadd("hackers", 1965, "Yukihiro Matsumoto");
			redis.zadd("hackers", 1916, "Claude Shannon");
			redis.zadd("hackers", 1969, "Linus Torvalds");
			redis.zadd("hackers", 1912, "Alan Turing");
			Set<String> setValues = redis.zrange("hackers", 0, -1);
			System.out.println(setValues);//[Alan Turing, Claude Shannon, Alan Kay, Richard Stallman, Yukihiro Matsumoto, Linus Torvalds]
			Set<String> setValues2 = redis.zrevrange("hackers", 0, -1);
			System.out.println(setValues2);//[Linus Torvalds, Yukihiro Matsumoto, Richard Stallman, Alan Kay, Claude Shannon, Alan Turing]
			
			// �������
			System.out.println(redis.flushDB());//ok
			// �������
			redis.zadd("zset", 10.1, "hello");
			redis.zadd("zset", 10.0, ":");
			redis.zadd("zset", 9.0, "zset");
			redis.zadd("zset", 11.0, "zset!");
			// Ԫ�ظ���
			System.out.println(redis.zcard("zset"));//4
			// Ԫ���±�
			System.out.println(redis.zscore("zset", "zset"));//9.0
			// �����Ӽ�
			System.out.println(redis.zrange("zset", 0, -1));//[zset, :, hello, zset!]
			// ɾ��Ԫ��
			System.out.println(redis.zrem("zset", "zset!"));//1
			System.out.println(redis.zcount("zset", 9.5, 10.5));//2
			// ��������ֵ
			System.out.println(redis.zrange("zset", 0, -1));//[zset, :, hello]
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(redis,IP,PORT);
		}

		
	}

	/**
	 * hashmap�������ݲ���
	 * ���ݲ������Ȼ��ܣ�keyֵ�Ƿ���ڼ�飬�������е�keyֵ/valueֵ
	 * ����ָ����keyֵ
	 * 
	 */
	public static void testHsh() {
		Jedis redis = RedisUtil.getJedis(IP, PORT);
		try {
			Map<String, String> pairs = new HashMap<String, String>();
			pairs.put("name", "Akshi");
			pairs.put("age", "2");
			pairs.put("sex", "Female");
			redis.hmset("kid", pairs);
			System.out.println(redis.hmget("kid", "pwd")); //null
			System.out.println(redis.hlen("kid")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���//3
			System.out.println(redis.exists("kid"));// �Ƿ����keyΪkid�ļ�¼//true
			System.out.println(redis.hkeys("kid"));// ����map�����е�����key//[sex, age, name]
			System.out.println(redis.hvals("kid"));// ����map�����е�����value//[Female, Akshi, 2]

			Iterator<String> iter = redis.hkeys("kid").iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				System.out.println(key + ":" + redis.hmget("kid", key));
				//sex:[Female]
				//age:[2]
				//name:[Akshi]
			}

			List<String> values = redis.hmget("kid", new String[] { "name", "age", "sex" });
			System.out.println(values);//[Akshi, 2, Female]
			Set<String> setValues = redis.hkeys("kid");
			System.out.println(setValues);//[sex, age, name]
			List<String> value = redis.hvals("kid");
			System.out.println(value);//[Female, Akshi, 2]
			pairs = redis.hgetAll("kid");
			System.out.println(pairs);//{sex=Female, age=2, name=Akshi}
			
			// �������
			System.out.println(redis.flushDB());//ok
			// �������
			redis.hset("hashs", "entryKey", "entryValue");
			redis.hset("hashs", "entryKey1", "entryValue1");
			redis.hset("hashs", "entryKey2", "entryValue2");
			// �ж�ĳ��ֵ�Ƿ����
			System.out.println(redis.hexists("hashs", "entryKey"));//true
			// ��ȡָ����ֵ
			System.out.println(redis.hget("hashs", "entryKey")); // ������ȡָ����ֵ//entryValue
			System.out.println(redis.hmget("hashs", "entryKey", "entryKey1"));//[entryValue, entryValue1]
			// ɾ��ָ����ֵ
			System.out.println(redis.hdel("hashs", "entryKey"));//1
			// Ϊkey�е��� field ��ֵ�������� increment
			System.out.println(redis.hincrBy("hashs", "entryKey", 321));//321
			// ��ȡ���е�keys
			System.out.println(redis.hkeys("hashs"));//[entryKey2, entryKey, entryKey1]
			// ��ȡ���е�values
			System.out.println(redis.hvals("hashs"));//[entryValue1, entryValue2, 321]
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(redis,IP,PORT);
		}
	}

	/**
	 * keys���ͨ������� 
	 * ����ʱ����� 
	 * keyֵrename����
	 * �������
	 */
	public static void testOther() {
		Jedis redis = RedisUtil.getJedis(IP, PORT);

		try {
			// keys�д���Ŀ�����ͨ���
			System.out.println(redis.keys("*")); // ���ص�ǰ�������е�key 
			System.out.println(redis.keys("*name"));// ���ص�sname [sname, name]
			System.out.println(redis.del("sanmdde"));// ɾ��keyΪsanmdde�Ķ��� ɾ���ɹ�����1
														// ɾ��ʧ�ܣ����߲����ڣ����� 0
			System.out.println(redis.ttl("hashs"));// ���ظ���key����Чʱ�䣬�����-1���ʾ��Զ��Ч
			redis.setex("timekey", 10, "min");// ͨ���˷���������ָ��key�Ĵ���Чʱ�䣩 ʱ��Ϊ��
			Thread.sleep(5000);// ˯��5���ʣ��ʱ�佫Ϊ<=5
			System.out.println(redis.ttl("timekey")); // ������Ϊ5
			redis.setex("timekey", 1, "min"); // ��Ϊ1�������ٿ�ʣ��ʱ�����1��
			System.out.println(redis.ttl("timekey")); // ������Ϊ1
			System.out.println(redis.exists("key"));// ���key�Ƿ����
			System.out.println(redis.rename("timekey", "time"));
			System.out.println(redis.get("timekey"));// ��Ϊ�Ƴ�������Ϊnull
			System.out.println(redis.get("time")); // ��Ϊ��timekey ������Ϊtime
													// ���Կ���ȡ��ֵ min
			// jedis ����
			// ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�
			redis.del("a");// ��������ݣ��ټ������ݽ��в���
			redis.rpush("a", "1");
			redis.lpush("a", "6");
			redis.lpush("a", "3");
			redis.lpush("a", "9");
			System.out.println(redis.lrange("a", 0, -1));// [9, 3, 6, 1]
			System.out.println(redis.sort("a")); // [1, 3, 6, 9] //�����������
			System.out.println(redis.lrange("a", 0, -1));//[9, 3, 6, 1]
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(redis,IP,PORT);
		}

	}

	/**
	 * ��ֵset����ʹ��Pipeline
	 * Ч�ʵ���
	 */
	public static void testUnUsePipeline() {
		long start = new Date().getTime();

		Jedis redis = RedisUtil.getJedis(IP, PORT);
		for (int i = 0; i < 10000; i++) {
			redis.set("age1" + i, i + "");
			System.out.println(redis.get("age1" + i));// ÿ�����������������redis-server
		}
		long end = new Date().getTime();

		System.out.println("unuse pipeline cost:" + (end - start) + "ms");

		RedisUtil.closeJedis(redis,IP,PORT);
	}

	
	/**
	 * ʹ��Pipeline
	 * ��ȶ��Ը�Ч��
	 */
	public static void testUsePipeline() {
		long start = new Date().getTime();

		Jedis redis = RedisUtil.getJedis(IP, PORT);
		redis.flushDB();
		Pipeline p = redis.pipelined();
		for (int i = 0; i < 10000; i++) {
			p.set("age2" + i, i + "");
			System.out.println(p.get("age2" + i));
		}
		p.sync();// ��δ����ȡ���е�response

		long end = new Date().getTime();

		System.out.println("use pipeline cost:" + (end - start) + "ms");

		RedisUtil.closeJedis(redis,IP,PORT);
	}


	/**
	 * ʱ�临�Ӷȣ�
		  O(N+M*log(M))�� N ΪҪ������б�򼯺��ڵ�Ԫ�������� M ΪҪ���ص�Ԫ��������
		    ���ֻ��ʹ�� SORT ����� GET ѡ���ȡ���ݶ�û�н�������ʱ�临�Ӷ� O(N)��
		    �������Ͳ���������ͨ��ʹ��limit�޶����з�ҳ���ܲ�����
	 */
	public static void testSort1() {
		// ����Ĭ����������Ϊ����ֵ������Ϊ˫���ȸ�������Ȼ����бȽ�
		Jedis redis = RedisUtil.getJedis(IP, PORT);
		// һ��SORT�÷� ��򵥵�SORTʹ�÷�����SORT key��
		redis.lpush("mylist", "1");
		redis.lpush("mylist", "4");
		redis.lpush("mylist", "6");
		redis.lpush("mylist", "3");
		redis.lpush("mylist", "0");
		//�����������ͻ�limit����ֹλ��. 
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.desc();
		// sortingParameters.alpha();//�����ݼ��б�������ַ���ֵʱ��������� ALPHA
		// ���η�(modifier)��������
		sortingParameters.limit(0, 2);// �����ڷ�ҳ��ѯ
		List<String> list = redis.sort("mylist", sortingParameters);// Ĭ��������
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		redis.flushDB();
		RedisUtil.closeJedis(redis,IP,PORT);
	}

	/**
	 * sort list
	 * LIST���hash������
	 * ����ָ����SortingParams����ʽ�����������������ݸ�ʽ���з���
	 * ������ϵ����
	 * �������Ϊstudentlist����ѧ�����������ݼ���
	 * hset���������Խ�ĳ��ѧ��user:id�µ�ĳ����ѧ�ƣ��ĳɼ�(value)�������ݱ���
	 * ����ƥ�������������Ӧ�ĳɼ��������
	 */
	public static void testSort2() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.del("user:66", "user:55", "user:33", "user:22", "user:11", "userlist");
		jedis.lpush("studentlist", "33");
		jedis.lpush("studentlist", "22");
		jedis.lpush("studentlist", "55");
		jedis.lpush("studentlist", "11");

		//����ϣ�� key �е��� field ��ֵ��Ϊ value �� ��� key �����ڣ�һ���µĹ�ϣ������������ HSET ������ ����� field �Ѿ������ڹ�ϣ���У���ֵ�������ǡ�
		jedis.hset("user:66", "math", "66");
		jedis.hset("user:55", "math", "55");
		jedis.hset("user:33", "math", "33");
		jedis.hset("user:22", "math", "22");
		jedis.hset("user:11", "math", "11");//ѧ��user:��ţ���field��ĳ��ѧ��-��ѧ/Ӣ��ϵĳɼ�(value)
		jedis.hset("user:11", "english", "110");
		jedis.hset("user:22", "english", "220");
		jedis.hset("user:33", "english", "330");
		jedis.hset("user:55", "english", "550");
		jedis.hset("user:66", "english", "660");

		SortingParams sortingParameters = new SortingParams();
		// ���� "->" ���ڷָ��ϣ��ļ���(key name)��������(hash field)����ʽΪ "key->field" ��
		sortingParameters.desc();
		sortingParameters.get("user:*->math");
		sortingParameters.get("user:*->english");
		List<String> result = jedis.sort("studentlist", sortingParameters);
		for (String item : result) {
			System.out.println("item...." + item);
		}
		/**
		 * ��Ӧ��redis�ͻ��������ǣ�sort ml get user*->name sort ml get user:*->name get
		 * user:*->add
		 */
	}

	
	/**
	 * sort set
	 * SET���String������
	 * REL��ϵ��ش������   �����б� ������Ϣ ���ѳɼ�������Ϣ��Ӽ���Ӧ���������
	 * �������id�����ѵ���ϸ��Ϣ�����ѵĶ�Ӧ�ɼ�
	 */
	public static void testSort3() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.del("tom:friend:list", "score:uid:123", "score:uid:456",
				"score:uid:789", "score:uid:101", "uid:123", "uid:456",
				"uid:789", "uid:101");

		jedis.sadd("tom:friend:list", "123"); // tom�ĺ����б�
		jedis.sadd("tom:friend:list", "456");
		jedis.sadd("tom:friend:list", "789");
		jedis.sadd("tom:friend:list", "101");

		jedis.set("score:uid:123", "1000"); // ���Ѷ�Ӧ�ĳɼ�
		jedis.set("score:uid:456", "6000");
		jedis.set("score:uid:789", "100");
		jedis.set("score:uid:101", "5999");

		jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // ���ѵ���ϸ��Ϣ
		jedis.set("uid:456", "{'uid':456,'name':'jack'}");
		jedis.set("uid:789", "{'uid':789,'name':'jay'}");
		jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

		SortingParams sortingParameters = new SortingParams();

		sortingParameters.desc();
		// sortingParameters.limit(0, 2);
		// ע��GET����������ģ�GET user_name_* GET user_password_*
		// �� GET user_password_* GET user_name_*���صĽ��λ�ò�ͬ
		sortingParameters.get("#");// GET ����һ������Ĺ��򡪡� "GET #"
									// �����ڻ�ȡ���������(tom:friend:list)(��������������� user_id )�ĵ�ǰԪ�ء�
		sortingParameters.get("uid:*");
		sortingParameters.get("score:uid:*");
		sortingParameters.by("score:uid:*");
		// ��Ӧ��redis ������./redis-cli sort tom:friend:list by score:uid:* get # get
		// uid:* get score:uid:*
		List<String> result = jedis.sort("tom:friend:list", sortingParameters);
		for (String item : result) {
			System.out.println("item..." + item);
		}

	}

	/**
	 * 
	 ���������� Ĭ������£� SORT ����ֻ�Ǽ򵥵ط����������������ϣ�����������������Ը� STORE ѡ��ָ��һ�� key
	 * ��Ϊ�����������������б����ʽ�����浽��� key �ϡ�(��ָ�� key �Ѵ��ڣ��򸲸ǡ�) 
	 * redis> EXISTS user_info_sorted_by_level 
	 * # ȷ��ָ��key������ (integer) 0 
	 * redis> SORT user_id BY user_level_* GET # GET user_name_* GET user_password_* STORE user_info_sorted_by_level 
	 * # ���� (integer) 12 # ��ʾ��12������������� 
	 * redis> LRANGE user_info_sorted_by_level 0 11 
	 * # �鿴������ 1) "59230" 2) "jack" 3) "jack201022" 4) "2" 5) "huangz" 6) "nobodyknows" 7) "222" 8) "hacker" 9) "hey,im in" 10) "1" 11) "admin" 12) "a_long_long_password" 
	 * һ����Ȥ���÷��ǽ� SORT������棬�� EXPIRE Ϊ�������������ʱ�䣬����������ͳ��� SORT ������һ�����档 �����Ͳ���Ƶ���ص��� SORT �����ˣ�ֻ�е����������ʱ������Ҫ�ٵ���һ�� SORT ������
	 * ��ʱ��Ϊ����ȷʵ����һ�÷����������Ҫ�����Ա������ͻ���ͬʱ���л����ؽ�(Ҳ���Ƕ���ͻ��ˣ�ͬһʱ����� SORT������������Ϊ�����)������μ� SETNX ���
	 * 
	 * ��һ�����ݣ����������ݽ���������ش���������ִ��sortʱ�����������ݱ��浽һ����ʱ��key�У����Ҹ����keyһ���Ĺ���ʱ�䣬���ܸ���ִ��ʱ���ڣ����������ʱ����
	 */
	
	public static void testSort5() {
		// ����Ĭ����������Ϊ����ֵ������Ϊ˫���ȸ�������Ȼ����бȽ�
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		// һ��SORT�÷� ��򵥵�SORTʹ�÷�����SORT key��
		jedis.lpush("mylist", "1");
		jedis.lpush("mylist", "4");
		jedis.lpush("mylist", "6");
		jedis.lpush("mylist", "3");
		jedis.lpush("mylist", "0");
		// List<String> list = redis.sort("sort");// Ĭ��������
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.desc();
		// sortingParameters.alpha();//�����ݼ��б�������ַ���ֵʱ��������� ALPHA
		// ���η�(modifier)��������
		// sortingParameters.limit(0, 2);//�����ڷ�ҳ��ѯ

		// û��ʹ�� STORE �����������б���ʽ��������. ʹ�� STORE ������������������Ԫ��������

		jedis.sort("mylist", sortingParameters, "mylist");// �����ָ����������һ��KEY�У����ｲ�������ԭ����KEY

		List<String> list = jedis.lrange("mylist", 0, -1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		
		jedis.sadd("tom:friend:list", "123"); // tom�ĺ����б�
		jedis.sadd("tom:friend:list", "456");
		jedis.sadd("tom:friend:list", "789");
		jedis.sadd("tom:friend:list", "101");

		jedis.set("score:uid:123", "1000"); // ���Ѷ�Ӧ�ĳɼ�
		jedis.set("score:uid:456", "6000");
		jedis.set("score:uid:789", "100");
		jedis.set("score:uid:101", "5999");

		jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // ���ѵ���ϸ��Ϣ
		jedis.set("uid:456", "{'uid':456,'name':'jack'}");
		jedis.set("uid:789", "{'uid':789,'name':'jay'}");
		jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

		sortingParameters = new SortingParams();
		// sortingParameters.desc();
		sortingParameters.get("#");// GET ����һ������Ĺ��򡪡� "GET #" // �����ڻ�ȡ���������(��������������� user_id )�ĵ�ǰԪ�ء�
		sortingParameters.by("score:uid:*");
		jedis.sort("tom:friend:list", sortingParameters, "tom:friend:list");
		List<String> result = jedis.lrange("tom:friend:list", 0, -1);
		for (String item : result) {
			System.out.println("item..." + item);
		}

		jedis.flushDB();
		RedisUtil.closeJedis(jedis,IP,PORT);
	}
	
	
	/**
	 * ������������Ի�ȡ�������������Ϣ��
	 * @param start
	 * @param num_items
	 * @return
	 */
	public static List<String> get_latest_comments(int start, int num_items){
		//��ȡ��������
		//LPUSH latest.comments <ID> 
		//-���ǽ��б�ü�Ϊָ�����ȣ����Redisֻ��Ҫ�������µ�5000�����ۣ�
		//LTRIM latest.comments 0 5000 
		//���������Ʋ��ܳ���5000��ID��������ǵĻ�ȡID������һֱѯ��Redis��ֻ����start/count���������������Χ��ʱ�򣬲���Ҫȥ�������ݿ⡣
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		List<String> id_list = jedis.lrange("latest.comments",start,start+num_items-1) ;
		
		if(id_list.size()<num_items){
			//id_list = SQL.EXECUTE("SELECT ... ORDER BY time LIMIT ...");
		}
		return id_list;
	}
		   
	/**
	 * redis���ݿ������Ϣ��ѯ 
	 */
	public static void testDB() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		System.out.println(jedis.select(0));// select db-index
											// ͨ������ѡ�����ݿ⣬Ĭ�����ӵ����ݿ�������0,Ĭ�����ݿ�����16��������1��ʾ�ɹ���0ʧ��
		System.out.println(jedis.dbSize());// dbsize ���ص�ǰ���ݿ��key����
		System.out.println(jedis.keys("*")); // ����ƥ��ָ��ģʽ������key
		System.out.println(jedis.randomKey());
		jedis.flushDB();// ɾ����ǰ���ݿ�������key,�˷�������ʧ�ܡ�����
		jedis.flushAll();// ɾ���������ݿ��е�����key���˷�������ʧ�ܡ���������

	}

	/**
	 * jedis.mget()��ز���
	 * mget(key1, key2,��, key N)�����ؿ��ж��string�����ǵ�����Ϊkey1��key2������value
	 */
	public static void testMget() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.flushDB();// ɾ����ǰ���ݿ�������key,�˷�������ʧ�ܡ�����

		jedis.rpush("ids", "aa");
		jedis.rpush("ids", "bb");
		jedis.rpush("ids", "cc");

		List<String> ids = jedis.lrange("ids", 0, -1);

		jedis.set("aa", "{'name':'zhoujie','age':20}");
		jedis.set("bb", "{'name':'yilin','age':28}");
		jedis.set("cc", "{'name':'lucy','age':21}");
		//���ؿ��ж��string�����ǵ�����Ϊkey1��key2������value
		List<String> list = jedis.mget(ids.toArray(new String[ids.size()]));
		System.out.println(list);
	}

	/**
	 * ��������lrange��list���з�ҳ����
	 * �򵥵�jedis��ص����ݷ�ҳ���ܲ�������ĳһ�����ݿ�ʼ����ȡ����������
	 * ע����push���ݵ�ʱ�򣬽���ͬ�������������ջʱ��֤����������ȷ
	 */
	
	public static void queryPageBy() {
		int pageNo = 4;
		int pageSize = 6;
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.del("a");
		for (int i = 1; i <= 30; i++) {
			jedis.rpush("a", i + "");
		}

		int start = pageSize * (pageNo - 1);// ��Ϊredis��listԪ��λ�û�����0
		int end = start + pageSize - 1;

		List<String> results = jedis.lrange("a", start, end);// ��start����start��һ��Ԫ�أ��������Ǹ�Ԫ��
		for (String str : results) {
			System.out.println(str);
		}

	}

	
	/**
	 * [��Redis listѹ��ID������ʵ�ʵ�����]
		������������� �����ǽ������󡱣��������Ǽ���Ϣ��ֱ��ѹ��Redis list����ͨ����Ӧ��ô����
		���ڶ�����ܱ�������ã�������һ��list��ά����ʱ��˳����һ�������б����������ֻҪ�б�Ҫ�����������������list�У��ȵȡ�
		�����ǻص�reddit.com�����ӣ����û��ύ�����ӣ����ţ���ӵ�list�У��и��ɿ��ķ���������ʾ��
		$ redis-cli incr next.news.id
		(integer) 1
		$ redis-cli set news:1:title "Redis is simple"
		OK
		$ redis-cli set news:1:url "http://code.google.com/p/redis"
		OK
		$ redis-cli lpush submitted.news 1
		OK
		��������һ��key�������׵õ�һ����һ�޶�������ID��Ȼ��ͨ����ID��������CΪ�����ÿ���ֶ�����һ��key������¶����IDѹ��submitted.news list��
		��ֻ��ţ��С�ԡ�������ο��ĵ��п��Զ������к�list�йص���������ɾ��Ԫ�أ���תlist������������ȡ������Ԫ�أ���ȻҲ������LLEN�õ�list�ĳ��ȡ�
		
	 *
	 * �����ϵ�����ݿ�ĵ���һ�����ݣ�����idΪ������	
	 * �Զ���һ��������id ,	jedis.incr(key)�����key��������mysql���ֹ�ϵ�����ݿ����������id
	 * �����ϵ�����ݿ��һ�����ݣ���������id��jedis.incr(key) ����Ӧ����������:jedis.get("ad:adinfo:" + adInfoId + ":title") jedis.get("ad:adinfo:" + adInfoId + ":url")
	 * �������ڲ�ѯ���š����ṹ�µ���������ʱ������ƥ���ѯ����
	 */
	public static void testListStrUsage() {
		String title = "alexgaoyh";
		String url = "http://git.oschina.net/alexgaoyh";
		Jedis jedis = RedisUtil.getJedis(IP, PORT);

		long adInfoId = jedis.incr("ad:adinfo:next.id");
		jedis.set("ad:adinfo:" + adInfoId + ":title", title);
		jedis.set("ad:adinfo:" + adInfoId + ":url", url);
		jedis.lpush("ad:adinfo", String.valueOf(adInfoId));

		String resultTitle = jedis.get("ad:adinfo:" + adInfoId + ":title");
		String resultUrl = jedis.get("ad:adinfo:" + adInfoId + ":url");
		List<String> ids = jedis.lrange("ad:adinfo", 0, -1);
		System.out.println(resultTitle);
		System.out.println(resultUrl);
		System.out.println(ids);

		/**
		 * dbsize���ص�������key����Ŀ�������Ѿ����ڵģ� ��redis-cli keys "*"��ѯ�õ�������Ч��key��Ŀ
		 */
		System.out.println(jedis.dbSize());

		//������е�key
		jedis.flushAll();
	}

	/**
	 * ������һ���򵥵ķ�������ÿ����ӱ�ǩ�Ķ�����һ����ǩID������֮���������Ҷ�ÿ�����еı�ǩ��һ�����ID��֮������ ����������ǵ�����ID
	 * 1000������������ǩtag 1,2,5��77���Ϳ������������������ϣ� 
	 * $ redis-cli sadd news:1000:tags 1
	 * (integer) 1 
	 * $ redis-cli sadd news:1000:tags 2 
	 * (integer) 1 
	 * $ redis-cli sadd news:1000:tags 5 
	 * (integer) 1 
	 * $ redis-cli sadd news:1000:tags 77
	 * (integer) 1 
	 * $ redis-cli sadd tag:1:objects 1000 
	 * (integer) 1 
	 * $ redis-cli sadd tag:2:objects 1000 
	 * (integer) 1 
	 * $ redis-cli sadd tag:5:objects 1000
	 * (integer) 1 
	 * $ redis-cli sadd tag:77:objects 1000 
	 * (integer) 1
	 * Ҫ��ȡһ����������б�ǩ����˼򵥣� 
	 * $ redis-cli smembers news:1000:tags 
	 * 1.5		2.1		3.77	4.2 
	 *  ����Щ����ȥ�����򵥵Ĳ�����Ȼ��ʹ����Ӧ��Redis��������ʵ�֡�
	 *  ��������Ҳ������һ��ͬʱӵ�б�ǩ1, 2,10��27�Ķ����б��������SINTER�����������������ڲ�ͬ����֮��ȡ��������
	 *  ���Ϊ��Ŀ������ֻ�裺 $ redis-cli sinter tag:1:objects tag:2:objects tag:10:objects tag:27:objects ... no result
	 * in our dataset composed of just one object ...
	 * ������ο��ĵ��п����ҵ��ͼ�����ص�����������˸���Ȥ��һץһ��ѡ�һ��Ҫ����SORT���Redis���Ϻ�list���ǿ�����ġ�
	 * 
	 * ��ϵ�����ݿ�˫���������  һ�����¶�Ӧ�����ǩ��һ����ǩ���Ա�������¹���
	 * ��Զ�����ݿ��ṹ
	 */
	
	public static void testSetUsage() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		
		//ĳ�����°����ı�ǩ
		jedis.sadd("zhongsou:news:1000:tags", "1");
		jedis.sadd("zhongsou:news:1000:tags", "2");
		jedis.sadd("zhongsou:news:1000:tags", "5");
		jedis.sadd("zhongsou:news:1000:tags", "77");
		jedis.sadd("zhongsou:news:2000:tags", "1");
		jedis.sadd("zhongsou:news:2000:tags", "2");
		jedis.sadd("zhongsou:news:2000:tags", "5");
		jedis.sadd("zhongsou:news:2000:tags", "77");
		jedis.sadd("zhongsou:news:3000:tags", "2");
		jedis.sadd("zhongsou:news:4000:tags", "77");
		jedis.sadd("zhongsou:news:5000:tags", "1");
		jedis.sadd("zhongsou:news:6000:tags", "5");

		//ĳ����ǩ�����Ķ�Ӧ������id
		jedis.sadd("zhongsou:tag:1:objects", 1000 + "");
		jedis.sadd("zhongsou:tag:2:objects", 1000 + "");
		jedis.sadd("zhongsou:tag:5:objects", 1000 + "");
		jedis.sadd("zhongsou:tag:77:objects", 1000 + "");

		jedis.sadd("zhongsou:tag:1:objects", 2000 + "");
		jedis.sadd("zhongsou:tag:2:objects", 2000 + "");
		jedis.sadd("zhongsou:tag:5:objects", 2000 + "");
		jedis.sadd("zhongsou:tag:77:objects", 2000 + "");

		//����һ�����ϵ�ȫ����Ա���ü��������и������ϵĽ���
		//���ĸ���ǩȫ����������������id����
		Set<String> sets = jedis.sinter("zhongsou:tag:1:objects",
				"zhongsou:tag:2:objects", "zhongsou:tag:5:objects",
				"zhongsou:tag:77:objects");
		System.out.println(sets);
		jedis.flushAll();
	}

	/**
	 * ���������µ�set���ϲ���
	 * �������У��������У���������� ĳ����Χ�µ����ݼ�������
	 */
	public static void testSortedSetUsage() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		//���򼯺ϣ����ݡ��ڶ�����������������
		jedis.zadd("zhongsou:hackers", 1940, "Alan Kay");
		jedis.zadd("zhongsou:hackers", 1953, "Richard Stallman");
		jedis.zadd("zhongsou:hackers", 1943, "Jay");
		jedis.zadd("zhongsou:hackers", 1920, "Jellon");
		jedis.zadd("zhongsou:hackers", 1965, "Yukihiro Matsumoto");
		jedis.zadd("zhongsou:hackers", 1916, "Claude Shannon");
		jedis.zadd("zhongsou:hackers", 1969, "Linus Torvalds");
		jedis.zadd("zhongsou:hackers", 1912, "Alan Turing");

		Set<String> hackers = jedis.zrange("zhongsou:hackers", 0, -1);
		System.out.println(hackers);
		//[Alan Turing, Claude Shannon, Jellon, Alan Kay, Jay, Richard Stallman, Yukihiro Matsumoto, Linus Torvalds]

		Set<String> hackers2 = jedis.zrevrange("zhongsou:hackers", 0, -1);
		System.out.println(hackers2);
		//[Linus Torvalds, Yukihiro Matsumoto, Richard Stallman, Jay, Alan Kay, Jellon, Claude Shannon, Alan Turing]

		// �������,��������Redis����score���ڸ����1920��֮���Ԫ�أ�������ֵҲ�����ˣ���
		Set<String> hackers3 = jedis.zrangeByScore("zhongsou:hackers", "-inf", "1920");
		System.out.println(hackers3);
		//[Alan Turing, Claude Shannon, Jellon]

		// ZREMRANGEBYSCORE ���������Ȼ����ã�����ȴ�ǳ����ã����᷵����ɾ����Ԫ��������
		//ɾ�������򼯺ϱ�����key����Сֵ�����ֵ(��)֮��ķ���������Ԫ�ء� ����������ɾ����Ԫ��������
		long num = jedis.zremrangeByScore("zhongsou:hackers", "-inf", "1920");
		System.out.println(num);//3

		jedis.flushAll();
	}

}

