package com.cdyykj.system.commons;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis帮助类
 * 
 * @author kimi
 * @dateTime 2013-3-6 上午11:38:48
 */
@Component
public class RedisUtils {

	/**
	 * redis模版,value为String型
	 * 
	 * @author kimi
	 * @dateTime 2012-8-7 下午2:40:22
	 */
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * redis模版,value为Object型
	 * 
	 * @author kimi
	 * @dateTime 2012-8-7 下午2:40:22
	 */
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * <p>
	 * Discription:[返回set集合内容]
	 * </p>
	 * 
	 * @param key
	 * @return Set<String>
	 */
	public Set<String> getSetValue(String key) {
		return stringRedisTemplate.boundSetOps(key).members();
	}

	/**
	 * <p>
	 * Discription:[返回list集合内容] 分页之后
	 * </p>
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> getListValue(String key, int start, int end) {
		return stringRedisTemplate.boundListOps(key).range(start, end);
	}

	/**
	 * <p>
	 * Discription:[返回map集合内容]
	 * </p>
	 * 
	 * @param key
	 * @return Map<Object, Object>
	 */
	public Map<Object, Object> getMapValue(String key) {
		return stringRedisTemplate.boundHashOps(key).entries();
	}

	/** 
	 * @Title: getValue 
	 * @Description: 根据KEY获取对应存储的值 
	 * @author wwu
	 * @date 2015年11月3日 下午10:54:21
	 * 参数说明 
	 * 
	 * @param key
	 * @return  
	 */
	public Object getValue(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 向mapkey当中添加一对键值
	 * 
	 * @author kimi
	 * @dateTime 2013-3-6 上午11:43:39
	 * @param mapKey
	 *            map集合对象
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void addKeyByMap(String mapKey, String key, Object value) {
		stringRedisTemplate.boundHashOps(mapKey).put(key, value);
	}

	/**
	 * 从mapkey当中删除对应KEY
	 * 
	 * @author kimi
	 * @dateTime 2013-3-6 上午11:43:39
	 * @param mapKey
	 *            map集合对象
	 * @param key
	 *            键
	 */
	public void delKeyByMap(String mapKey, String key) {
		stringRedisTemplate.boundHashOps(mapKey).delete(key);
	}

	/**
	 * 从mapkey当中删除对应KEY
	 * 
	 * @author kimi
	 * @dateTime 2013-3-6 上午11:43:39
	 * @param mapKey
	 *            map集合对象KEY
	 * @param key
	 *            键
	 * @return 返回键所对应的值
	 */
	public Object getValueByMap(String mapKey, String key) {
		return stringRedisTemplate.boundHashOps(mapKey).get(key);
	}
	/**
	 * 标记当前map中是否含有对应key
	 * @author chenbiao
	 * @date 2016年7月13日 下午3:39:38
	 * 参数说明 
	 * 
	 * @param mapKey
	 * @param key
	 * @return
	 */
	public boolean hasKeyByMap(String mapKey, String key) {
		return stringRedisTemplate.boundHashOps(mapKey).hasKey(key);
	}

	/**
	 * 验证KEY当中是否存在指定的KEY
	 * 
	 * @author kimi
	 * @dateTime 2012-8-7 下午3:02:37
	 * @param setKey
	 * @param valueKey
	 * @return 如果存在则返回true，如果不存在则返回false
	 */
	public boolean validateSetForKey(String setKey, Object valueKey) {
		if (validateStringKeyExists(setKey)) {
			return stringRedisTemplate.boundSetOps(setKey).isMember(valueKey);
		}
		return false;
	}

	/**
	 * 验证list存储的字符串当中是否存储有指定的字符串KEY
	 * 
	 * @author kimi
	 * @dateTime 2012-8-7 下午3:02:37
	 * @param setKey
	 * @param valueKey
	 * @return
	 */
	public boolean validateListForKey(String setKey, String valueKey) {
		boolean flag = false;
		List<String> list = (List<String>) stringRedisTemplate.boundListOps(setKey)
				.range(0, -1);
		for (int i = 0, j = list.size(); i < j; i++) {
			if (valueKey.equals(list.get(i))) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean validateListForKey(String setKey, Integer valueKey) {
		// boolean flag = false;
		return redisTemplate.boundListOps(setKey).range(0, -1)
				.contains(valueKey);
		// for (Object str : list) {
		// if (valueKey.equals(str)) {
		// flag = true;
		// break;
		// }
		// }
		// return flag;
	}

	/**
	 * 判断redis当中是否存在KEY
	 * 
	 * @author kimi
	 * @dateTime 2012-8-28 下午5:49:45
	 * @param key
	 * @return 存在则返回true，不存在则返回false
	 */
	public boolean validateStringKeyExists(String key) {
		return stringRedisTemplate.hasKey(key);
	}
	public boolean validateStringKeyExists2(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 获取redis中存储KEY对应的值
	 * 
	 * @author kimi
	 * @dateTime 2012-8-7 上午11:34:51
	 * @param keys
	 * @return
	 */
	public Long getValuesByRedis(String keys) {
		String str;
		if (null != (str = this.stringRedisTemplate.boundValueOps(keys).get(0, -1))
				&& !"".equals(str)) {
			return Long.parseLong(str);
		}
		return (long) 0;
	}

	/**
	 * 获取redis中存储KEY对应的值
	 * 
	 * @author kimi
	 * @dateTime 2012-8-7 上午11:34:51
	 * @param keys
	 * @return
	 */
	public Long getValuesByRedis2(String keys) {
		String str;
		if (null != (str = this.stringRedisTemplate.boundValueOps(keys).get())
				&& !"".equals(str)) {
			return Long.parseLong(str);
		}
		return (long) 0;
	}

	/**
	 * <p>
	 * Discription:[返回计数器值]
	 * </p>
	 * 
	 * @param key
	 * @return String
	 */
	public String getCounterValue(String key) {
		return stringRedisTemplate.boundValueOps(key).get(0, -1);
	}

	/**
	 * <p>
	 * Discription:[计数器清零]
	 * </p>
	 * 
	 * @param key
	 * @return String
	 */
	public void resetCounterValue(String key) {
		String a = stringRedisTemplate.boundValueOps(key).get(0, -1);
		int val = 0;
		if (null != a && !"".equals(a)) {
			val = -Integer.parseInt(a);
		}
		stringRedisTemplate.opsForValue().increment(key, val);
	}

	/**
	 * <p>
	 * Discription:[保存或更新值]
	 * </p>
	 * 
	 * @param key
	 */
	public void saveOrUpdateCounter(String key, long counter) {
		stringRedisTemplate.opsForValue().increment(key, counter);
	}

	/**
	 * <p>
	 * Discription:[保存或更新Set集合]
	 * </p>
	 * 
	 * @param key
	 * @param value
	 */
	public void saveOrUpdateSet(String key, String value) {
		stringRedisTemplate.boundSetOps(key).add(value);
	}

	/**
	 * <p>
	 * Discription:[保存或更新list集合]
	 * </p>
	 * 
	 * @param key
	 * @param value
	 */
	public void saveOrUpdateList(String key, String value) {
		stringRedisTemplate.boundListOps(key).leftPush(value);
	}

	/**
	 * <p>
	 * Discription:[保存或更新list集合]
	 * </p>
	 * 
	 * @param key
	 * @param value
	 */
	public void saveOrUpdateListByRight(String key, String value) {
		stringRedisTemplate.boundListOps(key).rightPush(value);
	}

	/**
	 * <p>
	 * Discription:[设置KEY，存放list]
	 * </p>
	 * 
	 * @param key
	 */
	public void setList(String key, List<String> obj) {
		if (validateStringKeyExists(key)) {
			stringRedisTemplate.delete(key);
		}
		if (null != obj && obj.size() > 0) {
			for (int i = 0, j = obj.size(); i < j; i++) {
				saveOrUpdateListByRight(key, obj.get(i));
			}
		}
	}

	/**
	 * <p>
	 * Discription:[设置KEY，存放list]
	 * </p>
	 * 
	 * @param key
	 */
	public void setList2(String key, List<ArrayNode> obj) {
		if (validateStringKeyExists(key)) {
			redisTemplate.delete(key);
		}
		if (null != obj && obj.size() > 0) {
			for (int i = 0, j = obj.size(); i < j; i++) {
				redisTemplate.boundListOps(key).rightPush(obj.get(i));
			}
		}
	}

	/**
	 * <p>
	 * Discription:[设置KEY，存放list]
	 * </p>
	 * 
	 * @param key
	 */
	public void setList3(String key, List<? extends Object> obj) {
		if (validateStringKeyExists(key)) {
			redisTemplate.delete(key);
		}
		if (null != obj && obj.size() > 0) {
			for (int i = 0, j = obj.size(); i < j; i++) {
				redisTemplate.boundListOps(key).rightPush(obj.get(i));
			}
		}
	}
	
	public Boolean setListT(String key, List<? extends Object> obj,Long seconds, TimeUnit timeUnit){
		if (validateStringKeyExists(key)) {
			redisTemplate.delete(key);
		}
		if (null != obj && obj.size() > 0) {
			for (int i = 0, j = obj.size(); i < j; i++) {
				redisTemplate.boundListOps(key).rightPush(obj.get(i));
			}
		}
		return redisTemplate.expire(key,seconds, timeUnit);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getListT(String key){
		if (validateStringKeyExists2(key)) {
			return (T) redisTemplate.boundListOps(key).range(0, -1);
		}
		return null;
	}


	public List<ArrayNode> getList(String key) throws Exception {
		List<Object> list = redisTemplate.boundListOps(key).range(0, -1);
		if (null != list && list.size() > 0) {
			List<ArrayNode> list2 = new LinkedList<ArrayNode>();
			for (int i = 0, j = list.size(); i < j; i++) {
				list2.add(JsonUtils.objectToArrayNode(list.get(i)));
			}
			return list2;
		}
		return null;
	}

	/**
	 * <p>
	 * Discription:[设置KEY，存放list]
	 * </p>
	 * 
	 * @param key
	 */
	public void setListByRedisTemplate2(String key, Object obj,
			boolean isLeftPush) {
		if (isLeftPush) {
			redisTemplate.boundListOps(key).leftPush(obj);
		} else {
			redisTemplate.boundListOps(key).rightPush(obj);
		}
	}

	public List<Object> getListByRedisTemplate2(String key) {
		return redisTemplate.boundListOps(key).range(0, -1);
	}

	/**
	 * <p>
	 * Discription:[保存或更新Set集合]
	 * </p>
	 * 
	 * @param key
	 * @param value
	 */
	public void deleKey(String key) {
		stringRedisTemplate.delete(key);//根据key删除缓存  
	}

	public void deleList(String key){
		redisTemplate.delete(key);
	}

	/**
	 * 从setKey当中删除对应value
	 * 
	 * @author kimi
	 * @dateTime 2013-3-6 上午11:43:39
	 * @param setKey
	 * @param value
	 * @return delKeyBySet
	 */
	public boolean delKeyBySet(String setKey, String value) {
		stringRedisTemplate.boundSetOps(setKey).remove(value);
		return true;
	}

	/**
	 * 设置KEY的生命周期。如果该KEY被修改过了，这个KEY的生命周期限制就不存在了。
	 * 
	 * @author kimi
	 * @dateTime 2013-1-30 下午5:47:06
	 * @param key
	 * @param seconds
	 * @param timeUnit
	 */
	public boolean setExpireKey(String key, Long seconds, TimeUnit timeUnit) {
		return stringRedisTemplate.expire(key, seconds, timeUnit);
	}

	/**
	 * 设置KEY的生命周期。
	 * <p>
	 * 这个设置为绝对生命周期的限制，到了date这个时间不管有没有被修改过，都会被销毁
	 * 
	 * @author kimi
	 * @dateTime 2013-1-30 下午5:47:06
	 * @param key
	 * @param seconds
	 * @param timeUnit
	 */
	public boolean setExpireKey(String key, Date date) {
		return stringRedisTemplate.expireAt(key, date);
	}

	/**
	 * 获取KEY剩余的生命周期
	 * <p>
	 * 
	 * @author kimi
	 * @dateTime 2013-1-30 下午5:47:06
	 * @param key
	 * @param seconds
	 * @param timeUnit
	 */
	public Long getExpireKey(String key) {
		return stringRedisTemplate.getExpire(key);
	}
	
	public Long getExpireKey(String key,TimeUnit timeUnit) {
		return stringRedisTemplate.getExpire(key, timeUnit);
	}

	// 根据KEY，存储字符串
	public void setStringForKey(String key, String str) {
		stringRedisTemplate.opsForValue().set(key, str);
	}

	public void setStringForKey(String key, Object str) {
		stringRedisTemplate.opsForValue().set(key, String.valueOf(str));
	}

	// 根据KEY，获取存储的字符串
	public String getStringForKey(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public Set<String> getKeys(String parrten) {
		return stringRedisTemplate.keys(parrten);
	}

	//将map写入缓存  
	public <T> void setMap(String key, Map<String, T> map,Long seconds, TimeUnit timeUnit){
		redisTemplate.delete(key);
		redisTemplate.opsForHash().putAll(key, map);
		redisTemplate.expire(key,seconds, timeUnit);

	}

	//获取map缓存   
	public <T> Map<String, T> getMap(String key, Class<T> clazz){  
		BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);   
		return boundHashOperations.size()==0?null:boundHashOperations.entries();  
	}

	// 将key存储的值加1
	public void increase(String key) {
		stringRedisTemplate.opsForValue().increment(key, 1);
	}
	
	/**
	 * 批量删除 
	* @Title: bathDel
	* @param @param pattern    正则
	* @return void    返回类型
	* @throws
	 */
	public void batchDel(String pattern) {
		Set<String> keys =  stringRedisTemplate.keys(pattern);
		for (String key : keys) {
			deleKey(key);
		}
	}
	
	public RedisTemplate<String, Object> getRedisTemplate(){
		return redisTemplate;
	}

	/**
	 * String转换成对象
	 * @param str
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T stringToBean(String str, Class<T> clazz) {
		if(str == null || str.length() <= 0 || clazz == null) {
			return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			return (T)str;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}
	public <T> String beanToString(T value) {
		if(value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			return ""+value;
		}else if(clazz == String.class) {
			return (String)value;
		}else if(clazz == long.class || clazz == Long.class) {
			return ""+value;
		}else {
			return JSON.toJSONString(value);
		}
	}
}
