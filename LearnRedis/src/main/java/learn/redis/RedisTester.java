package learn.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import learn.Driver;
import redis.clients.jedis.Jedis;

public class RedisTester {

	private final static String KEY_ACD_ALL = "StructParam:ACD:Keys";


	// filed identifiers for class StructParamACD, StructParamTimeZone
	private static final String FIELD_ACD_ID = "ACD_ID";
	private static final String FIELD_IS_PRIMARY = "IS_Primary";
	private static final String FIELD_ICWS_URL = "ICWS_URL";
	private static final String FIELD_ICWS_USER_ID = "ICWS_Userid";
	private static final String FIELD_ICWS_PASSWORD = "ICWS_Password";
	private static final String FIELD_CREATED_BY = "Created_By";
	private static final String FIELD_CREATED_ON = "Created_On";
	private static final String FIELD_LAST_UPDATED_ON = "Last_Updated_On";


	// acd
	public List<SomeModel> findAllACDlist() {
		List<SomeModel> retList = new ArrayList<SomeModel>();

		Jedis jedis = Driver.connect();
		// get all the ACD hash-keys from SET

		Set<String> keysSet = jedis.smembers(KEY_ACD_ALL);
		System.out.println("ACDTimeZoneDAOImpl: findAllACDlist: keysSet.length=" + keysSet.size());

		for (String hashKey : keysSet) {
			retList.add(createToStructParamACD(hashKey));
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
	private SomeModel createToStructParamACD(String hashKey) {
		System.out.println("ACDTimeZoneDAOImpl: createToStructParamACD: hashKey=" + hashKey);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Jedis jedis = Driver.connect();

		String acdId = jedis.hget(hashKey, FIELD_ACD_ID);
		String isPrimary = jedis.hget(hashKey, FIELD_IS_PRIMARY);
		String icwsURL = jedis.hget(hashKey, FIELD_ICWS_URL);
		String icwsUserId = jedis.hget(hashKey, FIELD_ICWS_USER_ID);
		String icwsPassword = jedis.hget(hashKey, FIELD_ICWS_PASSWORD);
		String createdBy = jedis.hget(hashKey, FIELD_CREATED_BY);
		String createdOn = jedis.hget(hashKey, FIELD_CREATED_ON);
		String lastUpdatedOn = jedis.hget(hashKey, FIELD_LAST_UPDATED_ON);

		SomeModel retVal = new SomeModel();
		retVal.setAcdId(Integer.valueOf(acdId));
		retVal.setIsPrimary(Integer.valueOf(isPrimary));
		retVal.setIcwsURL(icwsURL);
		retVal.setIcwsUserId(icwsUserId);
		retVal.setIcwsPassword(icwsPassword);
		retVal.setCreatedBy(createdBy);

		try {
			retVal.setCreatedOn(formatter.parse(createdOn));
			retVal.setLastUpdatedOn(formatter.parse(lastUpdatedOn));
		} catch (ParseException e) {
			System.err.println(e);
		}

		System.out.println("ACDTimeZoneDAOImpl: createToStructParamACD: retVal=" + retVal.toString());
		return retVal;
	}


	public void updateACD() {
		Jedis jedis = Driver.connect();
		String hashKey = "myset:ACD:1";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();

		// setup redis with old data
		jedis.sadd("StructParam:ACD:Keys", hashKey);
		jedis.hset(hashKey, "ACD_ID", "1");
		jedis.hset(hashKey, "IS_Primary", "1");
		jedis.hset(hashKey, "ICWS_URL", "http://IND133gacd073.corp.intuit.net:8018/icws");
		jedis.hset(hashKey, "ICWS_Userid", "mango");
		jedis.hset(hashKey, "ICWS_Password", "apple");
		jedis.hset(hashKey, "Created_By", "SYSTEM");
		jedis.hset(hashKey, "Created_On", "2018-03-31 10:00:04");
		jedis.hset(hashKey, "Last_Updated_On", "2018-03-31 10:00:04");

		Map<String, String> map = jedis.hgetAll(hashKey);
		System.out.println("ACDTimeZoneDAOImpl: createToStructParamACD: map=" + map);
		String temp = map.get("ICWS_Password1");
		System.out.println("@@@@@@@@@ =" + temp);

		
		// setup new data
		SomeModel structParamACD = new SomeModel();
		structParamACD.setAcdId(new Integer(2));
		structParamACD.setIsPrimary(new Integer(2));
		structParamACD.setIcwsURL("http://IND133gacd073.corp.intuit.net:8018/icws/custom");
		structParamACD.setIcwsUserId("new_usr");
		structParamACD.setIcwsPassword("new_pwd");
		structParamACD.setCreatedOn(new Date());
		structParamACD.setCreatedBy("new_user");
		structParamACD.setLastUpdatedOn(new Date());

		// update redis data
		Boolean isExists = jedis.hexists(hashKey, FIELD_ACD_ID);
		System.out
				.println("ACDTimeZoneDAOImpl: updateACD: hashKey=" + hashKey + ", exists=" + (isExists ? "YES" : "NO"));

		if (isExists) {
			jedis.hset(hashKey, FIELD_ACD_ID, "" + structParamACD.getAcdId());
			jedis.hset(hashKey, FIELD_IS_PRIMARY, "" + structParamACD.getIsPrimary());
			jedis.hset(hashKey, FIELD_ICWS_URL, structParamACD.getIcwsURL());
			jedis.hset(hashKey, FIELD_ICWS_USER_ID, structParamACD.getIcwsUserId());
			jedis.hset(hashKey, FIELD_ICWS_PASSWORD, structParamACD.getIcwsPassword());
			jedis.hset(hashKey, FIELD_CREATED_BY, structParamACD.getCreatedBy());
			jedis.hset(hashKey, FIELD_CREATED_ON, formatter.format(structParamACD.getCreatedOn()));
			jedis.hset(hashKey, FIELD_LAST_UPDATED_ON, formatter.format(currentDate));
			jedis.hset(hashKey, "dummy", formatter.format(currentDate));
		}

		// teardown
		jedis.del(hashKey);
	}
}
