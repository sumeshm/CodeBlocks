package learn;

import learn.redis.RedisTester;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Driver {
	private static final String 	REDIS_LOCAL_HOST = "localhost";
	private static final Integer 	REDIS_PORT = 6379;

	private static JedisPool jedisPool = null;
	private static Jedis jedis = null;
	
	public static Jedis connect() {
		if (jedisPool == null)
		{
			jedisPool = new JedisPool(new JedisPoolConfig(), REDIS_LOCAL_HOST, REDIS_PORT);
			System.out.println("REDIS Connected ...");
		}
		if (jedis == null)
		{
			jedis = jedisPool.getResource();
		}

		return jedis;
	}

	public static void disconnect() {
		if (jedisPool == null)
		{
			jedisPool.destroy();
			jedisPool = null;
			jedis = null;
		}

		System.out.println("REDIS Disconnected ...");
	}

	public static void main(String[] args) {
		System.out.println("Well well....here we go Redis...");

		
		connect();
		
		RedisTester util = new RedisTester();
//		util.findAllACDlist();
		util.updateACD();

		disconnect();
	}
}
