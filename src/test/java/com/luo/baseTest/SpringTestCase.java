package com.luo.baseTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.luo.service.IRedisService;

import redis.clients.jedis.JedisPoolConfig;

//指定bean注入的配置文件  
@ContextConfiguration(locations = { "classpath:application.xml" })  
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner  
@RunWith(SpringJUnit4ClassRunner.class)  
public class SpringTestCase extends AbstractJUnit4SpringContextTests {
	@Autowired
	JedisPoolConfig jedisPoolConfig;
	@Autowired
	RedisTemplate<?, ?>  redisTemplate;
	@Autowired
	IRedisService<String, Object> redisService;
	@Test
	public void testRedis(){
	    String key ="spring";
		System.out.println("key:"+redisTemplate.randomKey());
		//redisTemplate.keys("*")
		 ListOperations<String, String> lop = (ListOperations<String, String>) redisTemplate.opsForList();
		 RedisSerializer<String> serializer = new StringRedisSerializer();
		    redisTemplate.setKeySerializer(serializer);
		    redisTemplate.setValueSerializer(serializer);
		    // rt.setDefaultSerializer(serializer);

		    lop.leftPush(key, "aaa");
		    lop.leftPush(key, "bbb");
		    long size = lop.size(key);
		    //redisTemplate.boundHashOps(key).size();
		   
	}
	@Test
	public void test2(){
	    ValueOperations<String, String> vop = (ValueOperations<String, String>) redisTemplate.opsForValue();
	    String key = "string_redis_template";
	    String v = "use StringRedisTemplate set k v";
	    vop.set(key, v);
	    String value = vop.get(key);
	    System.out.println(value);
	}
	@Test
	public void testSet(){
      SetOperations<String, Object>	vop =	(SetOperations<String, Object>) redisTemplate.opsForSet();
      vop.add("love34", "I");
	}
	/**
	 * 
	 * <p><b>功能详细:</b> 消息订阅</p>
	 * <p><b>创建时间:</b> 2016-7-18 下午12:48:25</p>
	 * <p><b>返回类型: void</b></p>
	 * @author xiaojianliang
	 * @throws Exception 
	 */
	@Test
	public void testPublic() throws Exception{ 
/*		byte[] msg = "hello weibo".getBytes("UTF-8");
		byte[] channel ="weibo".getBytes("UTF-8");
		redisTemplate.getConnectionFactory().getConnection().publish(msg, channel);*/
		//发送消息
		redisTemplate.convertAndSend("weibo","you 道的!!");
		
	}
	
	@Test 
	public void getKeys(){
		//redisService.set("lo", "shab",0);
		redisService.get("string_redis_template");
	}
	

}