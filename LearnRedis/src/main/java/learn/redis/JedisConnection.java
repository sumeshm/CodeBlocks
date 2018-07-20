package learn.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnection {

	private static final String REDIS_LOCAL_HOST = "localhost";
	private static final Integer REDIS_PORT = 6379;

	private static JedisPool jedisPool = null;
	private static Jedis jedis = null;

	public static Jedis getJedisConnection()
	{
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

	private JedisConnection()
	{
		
	}
}
