package com.interview.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.interview.common.InputValidationException;
import com.interview.service.IExclusionService;
import com.interview.service.util.AvatarValidator;

public class ExclusionServiceImpl implements IExclusionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExclusionServiceImpl.class);

	Map<String, String> blacklistMap = new HashMap<>();

	@Autowired
	private AvatarValidator avatarValidator;

	@Override
	public boolean validate(String dob, String ssn) throws InputValidationException {
		LOGGER.info("Blacklist size:" + blacklistMap.size());
		boolean retVal = false;
		avatarValidator.validateRequest(ssn, dob);
		String retDOB = blacklistMap.get(ssn);
		if (null != retDOB) {
			LOGGER.info("SSN-DOB has been found in Blacklist");
			retVal = retDOB.equals(dob);
		}

		return retVal;
	}

	@Override
	public Map<String, String> addBlacklist(Map<String, String> ssnDobMap) throws InputValidationException {
		LOGGER.info("Blacklist size(start):" + blacklistMap.size());
		if (null == ssnDobMap || ssnDobMap.isEmpty()) {
			LOGGER.error("SSN-DOB map is invalid");
			throw new InputValidationException("", "", "SSN-DOB map is invalid");
		}

		ssnDobMap.forEach((ssn, dob) -> {
			avatarValidator.validateRequest(ssn, dob);
			blacklistMap.put(ssn, dob);
		});

		LOGGER.info("Blacklist size(end):" + blacklistMap.size());
		LOGGER.info(" --> " + blacklistMap.toString());
		return blacklistMap;
	}

	@Override
	public void clearBlacklist() {
		LOGGER.info("Blacklist size(start):" + blacklistMap.size());
		blacklistMap.clear();
		LOGGER.info("Blacklist size(end):" + blacklistMap.size());
	}
}
