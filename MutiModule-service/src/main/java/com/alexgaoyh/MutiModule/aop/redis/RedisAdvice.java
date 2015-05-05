package com.alexgaoyh.MutiModule.aop.redis;

import java.io.Serializable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisAdvice {
	
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	/**
	 * 在核心业务执行前执行，不能阻止核心业务的调用。
	 * @param joinPoint
	 */
	/*private void doBefore(JoinPoint joinPoint) {
		System.out.println("-----doBefore().invoke-----");
		System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of doBefore()------");
	}*/

	/**
	 * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
	 * 
	 * 注意：当核心业务抛异常后，立即退出，转向After Advice
	 * 执行完毕After Advice，再转到Throwing Advice
	 * Object[] getArgs：返回目标方法的参数  Signature getSignature：返回目标方法的签名 
	 * Object getTarget：返回被织入增强处理的目标对象 Object getThis：返回AOP框架为目标对象生成的代理对象
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	private Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		
		//返回值类型, add 方法将对应的Object 转换为 json 保存到 缓存中，在 get方法的时候，通过下面注释的返回值类型，将json 转换为对应的object
		//pjp.getTarget().getClass().getDeclaredMethod(pjp.getSignature().getName(),((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes()).getReturnType();
		
		// 输出    execution(DemoServiceImpl.insert(..))
		//pjp.toShortString();
		
		//跳转到这里的方法名 形如 insert selectByPrimaryKey
		//pjp.getTarget().getClass().getDeclaredMethod(pjp.getSignature().getName(),((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes()).getName();
		
		String baseKey = pjp.toShortString();
		
		Object[] args = pjp.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == Integer.class) {
        	System.out.println("key =  " + baseKey + "_"  + args[0]);
        	
        	ObjectMapper mapper = new ObjectMapper();
        	
        	Object obj = this.get(baseKey + "_"  +args[0]);

        	
        	if(obj == null) {
        		
        		//调用核心逻辑
        		Object retVal = pjp.proceed();
        		
        		this.add(baseKey + "_"  +args[0], mapper.writeValueAsString(retVal));
        		
        		System.out.println("缓存为空");
        		
        		return retVal;
        		
        	}else {
        		
        		System.out.println("缓存不为空");
        		
        		obj = mapper.readValue(obj.toString(), pjp.getTarget().getClass().getDeclaredMethod(pjp.getSignature().getName(),
        				((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes()).getReturnType());

        		return obj;
        	}
        	
        }
		return null;
		
	}

	/**
	 * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
	 * @param joinPoint
	 */
	/*private void doAfter(JoinPoint joinPoint) {
		System.out.println("-----doAfter().invoke-----");
		System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of doAfter()------");
	}*/
	
	/**
	 * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 * @param joinPoint
	 */
	/*private void doReturn(JoinPoint joinPoint) {
		System.out.println("-----doReturn().invoke-----");
		System.out.println(" 此处可以对返回值做进一步处理");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of doReturn()------");
	}*/
	
	/**
	 * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
	 * @param joinPoint
	 * @param ex
	 */
	/*private void doThrowing(JoinPoint joinPoint,Throwable ex) {
		System.out.println("-----doThrowing().invoke-----");
		System.out.println(" 错误信息："+ex.getMessage());
		System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of doThrowing()------");
	}*/
	
	
	/**
	 * 添加
	 * @param key
	 * @param value
	 */
	public void add(final String key, final String value) {
		if(redisTemplate != null) {
			redisTemplate.execute(new RedisCallback<Object>() {
				@Override
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					connection.set(
							redisTemplate.getStringSerializer().serialize(key),
							redisTemplate.getStringSerializer().serialize(value));
					return null;
				}
			});
		}
    }
	
	/**
     * 根据key获取对象
     */
    public Object get(final String key) {
    	return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				
				byte[] keyByte = redisTemplate.getStringSerializer().serialize(key);
				byte[] value = connection.get(keyByte);
				
				String str = redisTemplate.getStringSerializer().deserialize(value);
				
				return str;
			}
    	} );
    }  
	
	 /** 
     * 获取 RedisSerializer 
     * 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  
}
