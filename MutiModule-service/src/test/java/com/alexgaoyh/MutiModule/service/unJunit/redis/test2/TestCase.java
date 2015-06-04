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
 * 在不同的线程中使用相同的Jedis实例会发生奇怪的错误。但是创建太多的实现也不好因为这意味着会建立很多sokcet连接，
 * 也会导致奇怪的错误发生。单一Jedis实例不是线程安全的。为了避免这些问题，可以使用JedisPool,
 * JedisPool是一个线程安全的网络连接池。可以用JedisPool创建一些可靠Jedis实例，可以从池中拿到Jedis的实例。
 * 这种方式可以解决那些问题并且会实现高效的性能
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
	 * 入门第一步进行简单的set get方法测试
	 */
	public static void Hello() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		try {
			jedis.set("name", "minxr");
			String ss = jedis.get("name");
			System.out.println(ss);//minxr

			// 很直观，类似map 将jintao append到已经有的value之后
			jedis.append("name", "jintao");
			ss = jedis.get("name");
			System.out.println(ss);//minxrjintao

			// 2、直接覆盖原来的数据
			jedis.set("name", "jintao");
			System.out.println(jedis.get("name"));//jintao

			// 删除key对应的记录
			jedis.del("name");
			System.out.println(jedis.get("name"));// 执行结果：null

			/**
			 * mset相当于 jedis.set("name","minxr"); jedis.set("jarorwar","aaa");
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
	 * 检测key是否存在
	 */
	public static void testKey() {
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		System.out.println("=============key==========================");
		// 清空数据
		System.out.println(jedis.flushDB());//ok
		System.out.println(jedis.echo("foo"));//foo
		// 判断key否存在
		System.out.println(jedis.exists("foo"));//false
		jedis.set("key", "values");
		System.out.println(jedis.exists("key"));//true
		
		RedisUtil.closeJedis(jedis,IP,PORT);
	}

	/**
	 * 针对value域进行的部分操作： 追加数据，替换数据，删除数据，
	 */
	public static void testString() {
		System.out.println("==String==");
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		try {
			// 清空数据
			System.out.println(jedis.flushDB());//ok
			// 存储数据
			jedis.set("foo", "bar");
			System.out.println(jedis.get("foo"));//bar
			// 若key不存在，则存储
			jedis.setnx("foo", "foo not exits");
			System.out.println(jedis.get("foo"));//bar
			
			// 覆盖数据
			jedis.set("foo", "foo update");
			System.out.println(jedis.get("foo"));//foo update
			// 追加数据
			jedis.append("foo", " hello, world");
			System.out.println(jedis.get("foo"));//foo update hello, world
			
			// 设置key的有效期，并存储数据
			jedis.setex("foo", 2, "foo not exits");
			System.out.println(jedis.get("foo"));//foo not exits
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			System.out.println(jedis.get("foo"));//null
			
			
			// 获取并更改数据
			jedis.set("foo", "foo update");
			//保存新值并且返回旧值
			System.out.println(jedis.getSet("foo", "foo modify"));//foo update
			
			// 截取value的值
			System.out.println(jedis.getrange("foo", 1, 3));//oo
			
			System.out.println(jedis.del(new String[] { "foo", "foo1", "foo3" }));//1
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}

	/**
	 * 针对list集合进行的简单操作。
	 * 对key下的list集合进行：获取所有值，数组长度，排序。修改操作
	 * 形如java.util.ArrayList相似
	 */
	public static void testList() {
		Jedis jedis = RedisUtil.getJedis(IP,PORT);
		try {
			// 开始前，先移除所有的内容
			jedis.del("messages");
			jedis.rpush("messages", "Hello how are you?");
			jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");
			jedis.rpush("messages", "I should look into this NOSQL thing ASAP");

			// 再取出所有数据jedis.lrange是按范围取出，
			// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
			List<String> values = jedis.lrange("messages", 0, -1);
			System.out.println(values);
			//[Hello how are you?, Fine thanks. I'm having fun with redis., I should look into this NOSQL thing ASAP]
			
			// 清空数据
			System.out.println(jedis.flushDB());//ok
			// 添加数据
			jedis.lpush("lists", "1");
			jedis.lpush("lists", "3");
			jedis.lpush("lists", "2");
			// 数组长度
			System.out.println(jedis.llen("lists"));//3
			// 排序
			System.out.println(jedis.sort("lists"));//[1, 2, 3]
			// 字串
			System.out.println(jedis.lrange("lists", 0, 3));//[2, 3, 1]
			// 修改列表中单个值
			jedis.lset("lists", 0, "hello list!");
			// 获取列表指定下标的值
			System.out.println(jedis.lindex("lists", 1));//3
			// 删除列表指定下标的值
			System.out.println(jedis.lrem("lists", 1, "1"));//1
			// 删除区间以外的数据
			System.out.println(jedis.ltrim("lists", 0, 1));//ok
			// 列表出栈
			System.out.println(jedis.lpop("lists"));//hello list
			// 整个列表值
			System.out.println(jedis.lrange("lists", 0, -1));//[3]

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}
	}

	/**
	 * 针对set集合进行简单的功能测试(未排序的set集合)
	 * 数据添加，数据移除，数据是否存在， 数据列表获取，删除指定元素， 数据出栈， 集合求交集/并集/差集
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

			// 移除noname
			jedis.srem("myset", "4");
			System.out.println(jedis.smembers("myset"));// 获取所有加入的value//[3, 2, 1]
			
			System.out.println(jedis.sismember("myset", "4"));// 判断 minxr//false // 是否是sname集合的元素
			
			System.out.println(jedis.scard("sname"));// 返回集合的元素个数//0

			// 清空数据
			System.out.println(jedis.flushDB());//ok
			// 添加数据
			jedis.sadd("sets", "HashSet");
			jedis.sadd("sets", "SortedSet");
			jedis.sadd("sets", "TreeSet");
			// 判断value是否在列表中
			System.out.println(jedis.sismember("sets", "TreeSet"));//true
			;
			// 整个列表值
			System.out.println(jedis.smembers("sets"));//[SortedSet, TreeSet, HashSet]
			// 删除指定元素
			System.out.println(jedis.srem("sets", "SortedSet"));//1
			// 出栈
			System.out.println(jedis.spop("sets"));//HashSet
			System.out.println(jedis.smembers("sets"));//[TreeSet]
			//
			jedis.sadd("sets1", "HashSet1");
			jedis.sadd("sets1", "SortedSet1");
			jedis.sadd("sets1", "TreeSet");
			jedis.sadd("sets2", "HashSet2");
			jedis.sadd("sets2", "SortedSet1");
			jedis.sadd("sets2", "TreeSet1");
			// 交集
			System.out.println(jedis.sinter("sets1", "sets2"));//[SortedSet1]
			// 并集
			System.out.println(jedis.sunion("sets1", "sets2"));//[HashSet2, HashSet1, TreeSet1, SortedSet1, TreeSet]
			// 差集
			System.out.println(jedis.sdiff("sets1", "sets2"));//[TreeSet, HashSet1]
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(jedis,IP,PORT);
		}

	}

	/**
	 * 针对set集合进行相关操作（已排序）
	 * 根据score域进行数据集合的排序
	 * 数据汇总，获取score域值，删除元素，符合添加的数据汇总
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
			
			// 清空数据
			System.out.println(redis.flushDB());//ok
			// 添加数据
			redis.zadd("zset", 10.1, "hello");
			redis.zadd("zset", 10.0, ":");
			redis.zadd("zset", 9.0, "zset");
			redis.zadd("zset", 11.0, "zset!");
			// 元素个数
			System.out.println(redis.zcard("zset"));//4
			// 元素下标
			System.out.println(redis.zscore("zset", "zset"));//9.0
			// 集合子集
			System.out.println(redis.zrange("zset", 0, -1));//[zset, :, hello, zset!]
			// 删除元素
			System.out.println(redis.zrem("zset", "zset!"));//1
			System.out.println(redis.zcount("zset", 9.5, 10.5));//2
			// 整个集合值
			System.out.println(redis.zrange("zset", 0, -1));//[zset, :, hello]
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(redis,IP,PORT);
		}

		
	}

	/**
	 * hashmap集合数据操作
	 * 数据参数长度汇总，key值是否存在检查，返回所有的key值/value值
	 * 操作指定的key值
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
			System.out.println(redis.hlen("kid")); // 返回key为user的键中存放的值的个数//3
			System.out.println(redis.exists("kid"));// 是否存在key为kid的记录//true
			System.out.println(redis.hkeys("kid"));// 返回map对象中的所有key//[sex, age, name]
			System.out.println(redis.hvals("kid"));// 返回map对象中的所有value//[Female, Akshi, 2]

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
			
			// 清空数据
			System.out.println(redis.flushDB());//ok
			// 添加数据
			redis.hset("hashs", "entryKey", "entryValue");
			redis.hset("hashs", "entryKey1", "entryValue1");
			redis.hset("hashs", "entryKey2", "entryValue2");
			// 判断某个值是否存在
			System.out.println(redis.hexists("hashs", "entryKey"));//true
			// 获取指定的值
			System.out.println(redis.hget("hashs", "entryKey")); // 批量获取指定的值//entryValue
			System.out.println(redis.hmget("hashs", "entryKey", "entryKey1"));//[entryValue, entryValue1]
			// 删除指定的值
			System.out.println(redis.hdel("hashs", "entryKey"));//1
			// 为key中的域 field 的值加上增量 increment
			System.out.println(redis.hincrBy("hashs", "entryKey", 321));//321
			// 获取所有的keys
			System.out.println(redis.hkeys("hashs"));//[entryKey2, entryKey, entryKey1]
			// 获取所有的values
			System.out.println(redis.hvals("hashs"));//[entryValue1, entryValue2, 321]
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(redis,IP,PORT);
		}
	}

	/**
	 * keys相关通配符操作 
	 * 过期时间操作 
	 * key值rename操作
	 * 排序操作
	 */
	public static void testOther() {
		Jedis redis = RedisUtil.getJedis(IP, PORT);

		try {
			// keys中传入的可以用通配符
			System.out.println(redis.keys("*")); // 返回当前库中所有的key 
			System.out.println(redis.keys("*name"));// 返回的sname [sname, name]
			System.out.println(redis.del("sanmdde"));// 删除key为sanmdde的对象 删除成功返回1
														// 删除失败（或者不存在）返回 0
			System.out.println(redis.ttl("hashs"));// 返回给定key的有效时间，如果是-1则表示永远有效
			redis.setex("timekey", 10, "min");// 通过此方法，可以指定key的存活（有效时间） 时间为秒
			Thread.sleep(5000);// 睡眠5秒后，剩余时间将为<=5
			System.out.println(redis.ttl("timekey")); // 输出结果为5
			redis.setex("timekey", 1, "min"); // 设为1后，下面再看剩余时间就是1了
			System.out.println(redis.ttl("timekey")); // 输出结果为1
			System.out.println(redis.exists("key"));// 检查key是否存在
			System.out.println(redis.rename("timekey", "time"));
			System.out.println(redis.get("timekey"));// 因为移除，返回为null
			System.out.println(redis.get("time")); // 因为将timekey 重命名为time
													// 所以可以取得值 min
			// jedis 排序
			// 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
			redis.del("a");// 先清除数据，再加入数据进行测试
			redis.rpush("a", "1");
			redis.lpush("a", "6");
			redis.lpush("a", "3");
			redis.lpush("a", "9");
			System.out.println(redis.lrange("a", 0, -1));// [9, 3, 6, 1]
			System.out.println(redis.sort("a")); // [1, 3, 6, 9] //输入排序后结果
			System.out.println(redis.lrange("a", 0, -1));//[9, 3, 6, 1]
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisUtil.closeJedis(redis,IP,PORT);
		}

	}

	/**
	 * 多值set，不使用Pipeline
	 * 效率低下
	 */
	public static void testUnUsePipeline() {
		long start = new Date().getTime();

		Jedis redis = RedisUtil.getJedis(IP, PORT);
		for (int i = 0; i < 10000; i++) {
			redis.set("age1" + i, i + "");
			System.out.println(redis.get("age1" + i));// 每个操作都发送请求给redis-server
		}
		long end = new Date().getTime();

		System.out.println("unuse pipeline cost:" + (end - start) + "ms");

		RedisUtil.closeJedis(redis,IP,PORT);
	}

	
	/**
	 * 使用Pipeline
	 * 相比而言高效率
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
		p.sync();// 这段代码获取所有的response

		long end = new Date().getTime();

		System.out.println("use pipeline cost:" + (end - start) + "ms");

		RedisUtil.closeJedis(redis,IP,PORT);
	}


	/**
	 * 时间复杂度：
		  O(N+M*log(M))， N 为要排序的列表或集合内的元素数量， M 为要返回的元素数量。
		    如果只是使用 SORT 命令的 GET 选项获取数据而没有进行排序，时间复杂度 O(N)。
		    排序类型操作（可以通过使用limit限定进行分页功能操作）
	 */
	public static void testSort1() {
		// 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较
		Jedis redis = RedisUtil.getJedis(IP, PORT);
		// 一般SORT用法 最简单的SORT使用方法是SORT key。
		redis.lpush("mylist", "1");
		redis.lpush("mylist", "4");
		redis.lpush("mylist", "6");
		redis.lpush("mylist", "3");
		redis.lpush("mylist", "0");
		//定义排序类型或limit的起止位置. 
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.desc();
		// sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
		// 修饰符(modifier)进行排序。
		sortingParameters.limit(0, 2);// 可用于分页查询
		List<String> list = redis.sort("mylist", sortingParameters);// 默认是升序
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		redis.flushDB();
		RedisUtil.closeJedis(redis,IP,PORT);
	}

	/**
	 * sort list
	 * LIST结合hash的排序
	 * 根据指定的SortingParams排序方式，将符合条件的数据格式进行返回
	 * 关联关系处理，
	 * 可以理解为studentlist代表学生的整体数据集合
	 * hset方法，可以将某个学生user:id下的某个域（学科）的成绩(value)进行数据保存
	 * 根据匹配条件，将相对应的成绩进行输出
	 */
	public static void testSort2() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.del("user:66", "user:55", "user:33", "user:22", "user:11", "userlist");
		jedis.lpush("studentlist", "33");
		jedis.lpush("studentlist", "22");
		jedis.lpush("studentlist", "55");
		jedis.lpush("studentlist", "11");

		//将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。 如果域 field 已经存在于哈希表中，旧值将被覆盖。
		jedis.hset("user:66", "math", "66");
		jedis.hset("user:55", "math", "55");
		jedis.hset("user:33", "math", "33");
		jedis.hset("user:22", "math", "22");
		jedis.hset("user:11", "math", "11");//学生user:编号，在field域（某个学科-数学/英语）上的成绩(value)
		jedis.hset("user:11", "english", "110");
		jedis.hset("user:22", "english", "220");
		jedis.hset("user:33", "english", "330");
		jedis.hset("user:55", "english", "550");
		jedis.hset("user:66", "english", "660");

		SortingParams sortingParameters = new SortingParams();
		// 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。
		sortingParameters.desc();
		sortingParameters.get("user:*->math");
		sortingParameters.get("user:*->english");
		List<String> result = jedis.sort("studentlist", sortingParameters);
		for (String item : result) {
			System.out.println("item...." + item);
		}
		/**
		 * 对应的redis客户端命令是：sort ml get user*->name sort ml get user:*->name get
		 * user:*->add
		 */
	}

	
	/**
	 * sort set
	 * SET结合String的排序
	 * REL关系相关处理操作   好友列表 好友信息 好友成绩数据信息添加即相应的数据输出
	 * 输出好友id，好友的详细信息，好友的对应成绩
	 */
	public static void testSort3() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.del("tom:friend:list", "score:uid:123", "score:uid:456",
				"score:uid:789", "score:uid:101", "uid:123", "uid:456",
				"uid:789", "uid:101");

		jedis.sadd("tom:friend:list", "123"); // tom的好友列表
		jedis.sadd("tom:friend:list", "456");
		jedis.sadd("tom:friend:list", "789");
		jedis.sadd("tom:friend:list", "101");

		jedis.set("score:uid:123", "1000"); // 好友对应的成绩
		jedis.set("score:uid:456", "6000");
		jedis.set("score:uid:789", "100");
		jedis.set("score:uid:101", "5999");

		jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
		jedis.set("uid:456", "{'uid':456,'name':'jack'}");
		jedis.set("uid:789", "{'uid':789,'name':'jay'}");
		jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

		SortingParams sortingParameters = new SortingParams();

		sortingParameters.desc();
		// sortingParameters.limit(0, 2);
		// 注意GET操作是有序的，GET user_name_* GET user_password_*
		// 和 GET user_password_* GET user_name_*返回的结果位置不同
		sortingParameters.get("#");// GET 还有一个特殊的规则—— "GET #"
									// ，用于获取被排序对象(tom:friend:list)(我们这里的例子是 user_id )的当前元素。
		sortingParameters.get("uid:*");
		sortingParameters.get("score:uid:*");
		sortingParameters.by("score:uid:*");
		// 对应的redis 命令是./redis-cli sort tom:friend:list by score:uid:* get # get
		// uid:* get score:uid:*
		List<String> result = jedis.sort("tom:friend:list", sortingParameters);
		for (String item : result) {
			System.out.println("item..." + item);
		}

	}

	/**
	 * 
	 保存排序结果 默认情况下， SORT 操作只是简单地返回排序结果，如果你希望保存排序结果，可以给 STORE 选项指定一个 key
	 * 作为参数，排序结果将以列表的形式被保存到这个 key 上。(若指定 key 已存在，则覆盖。) 
	 * redis> EXISTS user_info_sorted_by_level 
	 * # 确保指定key不存在 (integer) 0 
	 * redis> SORT user_id BY user_level_* GET # GET user_name_* GET user_password_* STORE user_info_sorted_by_level 
	 * # 排序 (integer) 12 # 显示有12条结果被保存了 
	 * redis> LRANGE user_info_sorted_by_level 0 11 
	 * # 查看排序结果 1) "59230" 2) "jack" 3) "jack201022" 4) "2" 5) "huangz" 6) "nobodyknows" 7) "222" 8) "hacker" 9) "hey,im in" 10) "1" 11) "admin" 12) "a_long_long_password" 
	 * 一个有趣的用法是将 SORT结果保存，用 EXPIRE 为结果集设置生存时间，这样结果集就成了 SORT 操作的一个缓存。 这样就不必频繁地调用 SORT 操作了，只有当结果集过期时，才需要再调用一次 SORT 操作。
	 * 有时候为了正确实现这一用法，你可能需要加锁以避免多个客户端同时进行缓存重建(也就是多个客户端，同一时间进行 SORT操作，并保存为结果集)，具体参见 SETNX 命令。
	 * 
	 * 有一组数据，把这组数据进行数据相关处理排序，在执行sort时，将这组数据保存到一个临时的key中，并且给这个key一定的过期时间，就能搞在执行时间内，保存这个临时数据
	 */
	
	public static void testSort5() {
		// 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		// 一般SORT用法 最简单的SORT使用方法是SORT key。
		jedis.lpush("mylist", "1");
		jedis.lpush("mylist", "4");
		jedis.lpush("mylist", "6");
		jedis.lpush("mylist", "3");
		jedis.lpush("mylist", "0");
		// List<String> list = redis.sort("sort");// 默认是升序
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.desc();
		// sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA
		// 修饰符(modifier)进行排序。
		// sortingParameters.limit(0, 2);//可用于分页查询

		// 没有使用 STORE 参数，返回列表形式的排序结果. 使用 STORE 参数，返回排序结果的元素数量。

		jedis.sort("mylist", sortingParameters, "mylist");// 排序后指定排序结果到一个KEY中，这里讲结果覆盖原来的KEY

		List<String> list = jedis.lrange("mylist", 0, -1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		
		jedis.sadd("tom:friend:list", "123"); // tom的好友列表
		jedis.sadd("tom:friend:list", "456");
		jedis.sadd("tom:friend:list", "789");
		jedis.sadd("tom:friend:list", "101");

		jedis.set("score:uid:123", "1000"); // 好友对应的成绩
		jedis.set("score:uid:456", "6000");
		jedis.set("score:uid:789", "100");
		jedis.set("score:uid:101", "5999");

		jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
		jedis.set("uid:456", "{'uid':456,'name':'jack'}");
		jedis.set("uid:789", "{'uid':789,'name':'jay'}");
		jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

		sortingParameters = new SortingParams();
		// sortingParameters.desc();
		sortingParameters.get("#");// GET 还有一个特殊的规则—— "GET #" // ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
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
	 * 这个方法，可以获取到最近的评论信息数
	 * @param start
	 * @param num_items
	 * @return
	 */
	public static List<String> get_latest_comments(int start, int num_items){
		//获取最新评论
		//LPUSH latest.comments <ID> 
		//-我们将列表裁剪为指定长度，因此Redis只需要保存最新的5000条评论：
		//LTRIM latest.comments 0 5000 
		//们做了限制不能超过5000个ID，因此我们的获取ID函数会一直询问Redis。只有在start/count参数超出了这个范围的时候，才需要去访问数据库。
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		List<String> id_list = jedis.lrange("latest.comments",start,start+num_items-1) ;
		
		if(id_list.size()<num_items){
			//id_list = SQL.EXECUTE("SELECT ... ORDER BY time LIMIT ...");
		}
		return id_list;
	}
		   
	/**
	 * redis数据库相关信息查询 
	 */
	public static void testDB() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		System.out.println(jedis.select(0));// select db-index
											// 通过索引选择数据库，默认连接的数据库所有是0,默认数据库数是16个。返回1表示成功，0失败
		System.out.println(jedis.dbSize());// dbsize 返回当前数据库的key数量
		System.out.println(jedis.keys("*")); // 返回匹配指定模式的所有key
		System.out.println(jedis.randomKey());
		jedis.flushDB();// 删除当前数据库中所有key,此方法不会失败。慎用
		jedis.flushAll();// 删除所有数据库中的所有key，此方法不会失败。更加慎用

	}

	/**
	 * jedis.mget()相关操作
	 * mget(key1, key2,…, key N)：返回库中多个string（它们的名称为key1，key2…）的value
	 */
	public static void testMget() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.flushDB();// 删除当前数据库中所有key,此方法不会失败。慎用

		jedis.rpush("ids", "aa");
		jedis.rpush("ids", "bb");
		jedis.rpush("ids", "cc");

		List<String> ids = jedis.lrange("ids", 0, -1);

		jedis.set("aa", "{'name':'zhoujie','age':20}");
		jedis.set("bb", "{'name':'yilin','age':28}");
		jedis.set("cc", "{'name':'lucy','age':21}");
		//返回库中多个string（它们的名称为key1，key2…）的value
		List<String> list = jedis.mget(ids.toArray(new String[ids.size()]));
		System.out.println(list);
	}

	/**
	 * 可以利用lrange对list进行分页操作
	 * 简单的jedis相关的数据分页功能操作，从某一条数据开始，截取多少条数据
	 * 注意在push数据的时候，进行同类操作，队列入栈时保证数据排序正确
	 */
	
	public static void queryPageBy() {
		int pageNo = 4;
		int pageSize = 6;
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		jedis.del("a");
		for (int i = 1; i <= 30; i++) {
			jedis.rpush("a", i + "");
		}

		int start = pageSize * (pageNo - 1);// 因为redis中list元素位置基数是0
		int end = start + pageSize - 1;

		List<String> results = jedis.lrange("a", start, end);// 从start算起，start算一个元素，到结束那个元素
		for (String str : results) {
			System.out.println(str);
		}

	}

	
	/**
	 * [向Redis list压入ID而不是实际的数据]
		在上面的例子里 ，我们将“对象”（此例中是简单消息）直接压入Redis list，但通常不应这么做，
		由于对象可能被多次引用：例如在一个list中维护其时间顺序，在一个集合中保存它的类别，只要有必要，它还会出现在其他list中，等等。
		让我们回到reddit.com的例子，将用户提交的链接（新闻）添加到list中，有更可靠的方法如下所示：
		$ redis-cli incr next.news.id
		(integer) 1
		$ redis-cli set news:1:title "Redis is simple"
		OK
		$ redis-cli set news:1:url "http://code.google.com/p/redis"
		OK
		$ redis-cli lpush submitted.news 1
		OK
		我们自增一个key，很容易得到一个独一无二的自增ID，然后通过此ID创建对象–为对象的每个字段设置一个key。最后将新对象的ID压入submitted.news list。
		这只是牛刀小试。在命令参考文档中可以读到所有和list有关的命令。你可以删除元素，旋转list，根据索引获取和设置元素，当然也可以用LLEN得到list的长度。
		
	 *
	 * 形如关系型数据库的单表一行数据（主键id为自增）	
	 * 自定义一个自增的id ,	jedis.incr(key)，这个key可以形如mysql这种关系型数据库的自增主键id
	 * 形如关系型数据库的一行数据，自增主键id：jedis.incr(key) 他对应的数据内容:jedis.get("ad:adinfo:" + adInfoId + ":title") jedis.get("ad:adinfo:" + adInfoId + ":url")
	 * 这样，在查询这张‘表’结构下的所有数据时，可以匹配查询即可
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
		 * dbsize返回的是所有key的数目，包括已经过期的， 而redis-cli keys "*"查询得到的是有效的key数目
		 */
		System.out.println(jedis.dbSize());

		//清空所有的key
		jedis.flushAll();
	}

	/**
	 * 下面是一个简单的方案：对每个想加标签的对象，用一个标签ID集合与之关联，并且对每个已有的标签，一组对象ID与之关联。 例如假设我们的新闻ID
	 * 1000被加了三个标签tag 1,2,5和77，就可以设置下面两个集合： 
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
	 * 要获取一个对象的所有标签，如此简单： 
	 * $ redis-cli smembers news:1000:tags 
	 * 1.5		2.1		3.77	4.2 
	 *  而有些看上去并不简单的操作仍然能使用相应的Redis命令轻松实现。
	 *  例如我们也许想获得一份同时拥有标签1, 2,10和27的对象列表。这可以用SINTER命令来做，他可以在不同集合之间取出交集。
	 *  因此为达目的我们只需： $ redis-cli sinter tag:1:objects tag:2:objects tag:10:objects tag:27:objects ... no result
	 * in our dataset composed of just one object ...
	 * 在命令参考文档中可以找到和集合相关的其他命令，令人感兴趣的一抓一大把。一定要留意SORT命令，Redis集合和list都是可排序的。
	 * 
	 * 关系型数据库双向关联操作  一个文章对应多个标签，一个标签可以被多个文章关联
	 * 多对多的数据库表结构
	 */
	
	public static void testSetUsage() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		
		//某个文章包含的标签
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

		//某个标签包含的对应的文章id
		jedis.sadd("zhongsou:tag:1:objects", 1000 + "");
		jedis.sadd("zhongsou:tag:2:objects", 1000 + "");
		jedis.sadd("zhongsou:tag:5:objects", 1000 + "");
		jedis.sadd("zhongsou:tag:77:objects", 1000 + "");

		jedis.sadd("zhongsou:tag:1:objects", 2000 + "");
		jedis.sadd("zhongsou:tag:2:objects", 2000 + "");
		jedis.sadd("zhongsou:tag:5:objects", 2000 + "");
		jedis.sadd("zhongsou:tag:77:objects", 2000 + "");

		//返回一个集合的全部成员，该集合是所有给定集合的交集
		//这四个标签全部都被包含的文章id集合
		Set<String> sets = jedis.sinter("zhongsou:tag:1:objects",
				"zhongsou:tag:2:objects", "zhongsou:tag:5:objects",
				"zhongsou:tag:77:objects");
		System.out.println(sets);
		jedis.flushAll();
	}

	/**
	 * 排序条件下的set集合操作
	 * 正序排列，倒叙排列，区间操作（ 某个范围下的数据集合排序）
	 */
	public static void testSortedSetUsage() {
		Jedis jedis = RedisUtil.getJedis(IP, PORT);
		//有序集合：根据“第二个参数”进行排序。
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

		// 区间操作,我们请求Redis返回score介于负无穷到1920年之间的元素（两个极值也包含了）。
		Set<String> hackers3 = jedis.zrangeByScore("zhongsou:hackers", "-inf", "1920");
		System.out.println(hackers3);
		//[Alan Turing, Claude Shannon, Jellon]

		// ZREMRANGEBYSCORE 这个名字虽然不算好，但他却非常有用，还会返回已删除的元素数量。
		//删除的有序集合保存在key的最小值和最大值(含)之间的分数的所有元素。 返回整数，删除的元素数量。
		long num = jedis.zremrangeByScore("zhongsou:hackers", "-inf", "1920");
		System.out.println(num);//3

		jedis.flushAll();
	}

}

