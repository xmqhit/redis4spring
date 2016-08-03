package com.luo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


public abstract class AbstractRedisService<K,V> implements IRedisService<K, V> {
	@Autowired  
	private RedisTemplate<K, V> redisTemplate;  
	
	public RedisTemplate<K, V> getRedisTemplate() {  
	    return redisTemplate;  
	}  
	
	 
	 
	public void set(final K key, final V value, final long expiredTime) {  
	    BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);  
	    if (expiredTime <= 0) {  
	        valueOper.set(value);  
	    } else {  
	        valueOper.set(value, expiredTime, TimeUnit.MILLISECONDS);  
	    }  
	}  
	
	public V get(final K key) {  
	/*	try{
			 BoundValueOperations<K, V> valueOper = redisTemplate.boundValueOps(key);  
			    return  valueOper.get();
		}catch (Exception e) {*/
			 ValueOperations<K, V> vop = (ValueOperations<K, V>) redisTemplate.opsForValue();
                return  vop.get(key);
		/*}*/
	   
	}  
	
	public void del(K key) {  
	    if (redisTemplate.hasKey(key)) {  
	        redisTemplate.delete(key);  
	    }  
	}  
	public Boolean check(K key,V value){  
	 Boolean flag=false;  
	 if (redisTemplate.hasKey(key)) {  
	  if(value.equals(get(key))){  
	   flag=true;  
	  }  
	 }  
	 return flag;  
	}  

}
