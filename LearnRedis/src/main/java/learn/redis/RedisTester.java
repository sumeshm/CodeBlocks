package learn.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTester {
	
	private static Jedis jedis = null; 

	private final static String KEY_ALL = "SomeData:SomeModel:Keys";

	// filed identifiers for class StructParamACD, StructParamTimeZone
	private static final String FIELD_MODEL_NAME = "modelName";
	private static final String FIELD_MODEL_URL = "modelURL";

	// Setup
	static {
		jedis = JedisConnection.getJedisConnection();
		
		jedis.del("SomeData:SomeModel:Keys");
		jedis.sadd("SomeData:SomeModel:Keys", "SomeData:SomeModel:1");
		jedis.sadd("SomeData:SomeModel:Keys", "SomeData:SomeModel:2");
		jedis.sadd("SomeData:SomeModel:Keys", "SomeData:SomeModel:3");
		
		jedis.del("SomeData:SomeModel:1");
		jedis.del("SomeData:SomeModel:2");
		jedis.del("SomeData:SomeModel:3");
		jedis.hset("SomeData:SomeModel:1", FIELD_MODEL_NAME, "name-1");
		jedis.hset("SomeData:SomeModel:1", FIELD_MODEL_URL,   "url-1");
		jedis.hset("SomeData:SomeModel:2", FIELD_MODEL_NAME, "name-2");
		jedis.hset("SomeData:SomeModel:2", FIELD_MODEL_URL,   "url-2");
		jedis.hset("SomeData:SomeModel:3", FIELD_MODEL_NAME, "name-3");
		jedis.hset("SomeData:SomeModel:3", FIELD_MODEL_URL,   "url-3");
	}

	// get all the redis entries fo SomeModel
	public List<SomeModel> getSomeModelList() {
		List<SomeModel> retList = new ArrayList<SomeModel>();

		Set<String> keysSet = jedis.smembers(KEY_ALL);
		System.out.println("\nRedisTester: getSomeModelList: keysSet.length=" + keysSet.size());

		for (String hashKey : keysSet) {
			retList.add(createSomeModelObject(hashKey));
		}

		return retList;
	}


	/**
	 * Read redis-hash ACD data (key-value pairs) and build StructParamACD
	 * 
	 * @param hash-key
	 *            for the redis entry
	 * @return StructParamACD
	 */
	private SomeModel createSomeModelObject(String hashKey) {
		System.out.println("RedisTester: createSomeModelObject: hashKey=" + hashKey);

		Jedis jedis = JedisConnection.getJedisConnection();

		Map<String, String> map = jedis.hgetAll(hashKey);

		SomeModel retVal = new SomeModel();
		retVal.setModelName(map.get(FIELD_MODEL_NAME));
		retVal.setModelURL(map.get(FIELD_MODEL_URL));

		System.out.println("RedisTester: createSomeModelObject: retVal=" + retVal.toString());
		return retVal;
	}

	public void updateSomeModel() {
		String hashKey = "SomeData:SomeModel:1";

		Map<String, String> map = jedis.hgetAll(hashKey);
		System.out.println("\nRedisTester: updateSomeModel: old_map=" + map);

		// setup new data
		SomeModel newModel = new SomeModel();
		newModel.setModelName("new_name");
		newModel.setModelURL("new_url");

		// update redis data
		Boolean isExists = jedis.hexists(hashKey, FIELD_MODEL_NAME);
		System.out
				.println("RedisTester: updateSomeModel: hashKey=" + hashKey + ", exists=" + (isExists ? "YES" : "NO"));

		if (isExists) {
			jedis.hset(hashKey, FIELD_MODEL_NAME, newModel.getModelName());
			jedis.hset(hashKey, FIELD_MODEL_URL, newModel.getModelURL());
		}
		
		map = jedis.hgetAll(hashKey);
		System.out.println("RedisTester: updateSomeModel: new_map=" + map);
	}
}
