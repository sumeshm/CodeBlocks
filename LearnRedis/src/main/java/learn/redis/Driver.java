package learn.redis;

import redis.clients.jedis.Jedis;

public class Driver {
    public static final String hostname = "localhost";
    public static final int port = 6363;
    public static final int redisDB = 2;

	public static void main(String[] args) {
		System.out.println("Well well....here we go Redis...");
		
        Jedis jedis = new Jedis(hostname, port);
        //jedis.select(redisDB);
        //sassertThat(jedis.ping()).isEqualToIgnoringCase("PONG");
	}
}
