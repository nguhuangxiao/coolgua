package com.coolgua.signup.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.coolgua.common.service.RedisService;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public long del(String key) {
		Boolean delete = redisTemplate.delete(key);
		return delete ? 1 : 0;
	}

	@Override
	public long expire(String key, int second) {
		Boolean expire = redisTemplate.expire(key, second, TimeUnit.SECONDS);
		return expire ? 1 : 0;
	}

	@Override
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		return 0;
	}

	@Override
	public String hget(String hkey, String key) {
		return null;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		return 0;
	}

	@Override
	public long incr(String key) {
		return 0;
	}

	@Override
	public String set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
		return null;
	}

	@Override
	public String set(String key, String value, int second) {
		redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
		return null;
	}

	@Override
	public long ttl(String key) {
		return 0;
	}

}
