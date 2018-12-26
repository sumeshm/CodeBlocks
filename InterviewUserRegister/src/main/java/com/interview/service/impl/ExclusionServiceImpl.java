package com.interview.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.common.InputValidationException;
import com.interview.service.IExclusionService;
import com.interview.service.util.AvatarValidator;

public class ExclusionServiceImpl implements IExclusionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExclusionServiceImpl.class);

	Map<String, String> blacklistMap = new HashMap<>();

	@Override
	public boolean validate(String dob, String ssn) throws InputValidationException {
		return AvatarValidator.validateRequest(ssn, dob);
	}

	@Override
	public Map<String, String> addBlacklist(Map<String, String> ssnDobMap) throws InputValidationException {
		if (null == ssnDobMap || ssnDobMap.isEmpty()) {
			LOGGER.error("SSN-DOB map is invalid");
			throw new InputValidationException("", "", "SSN-DOB map is invalid");
		}

		ssnDobMap.forEach((ssn, dob) -> 
		{
			AvatarValidator.validateRequest(ssn, dob);
			blacklistMap.put(ssn, dob);
		}); 

		LOGGER.info("SSN-DOB blacklist map=" + blacklistMap.toString());
		return blacklistMap;
	}

	@Override
	public void clearBlacklist() {
		LOGGER.info("Claer Blacklist, currently having: " + blacklistMap.size() + " items");
		blacklistMap.clear();
	}
}
