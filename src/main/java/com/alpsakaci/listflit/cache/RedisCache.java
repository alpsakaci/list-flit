package com.alpsakaci.listflit.cache;

import redis.clients.jedis.Jedis;
import org.springframework.stereotype.Service;

@Service
public class RedisCache {

	private Jedis jedis = new Jedis();

	public Jedis getJedis() {
		return this.jedis;
	}
	
}
