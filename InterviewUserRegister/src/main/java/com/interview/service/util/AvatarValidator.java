package com.interview.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.common.InputValidationException;
import com.interview.model.AvatarRequest;

public class AvatarValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarValidator.class);

	// ISO 8601
	private static final String dobFormatData = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	private static final SimpleDateFormat dobFormat = new SimpleDateFormat(dobFormatData);

	// SSN
	private static final String regex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";

	public static boolean validateRequest(AvatarRequest avatarRequest) throws InputValidationException {
		if (isAvatarDuplicate(avatarRequest.getUserName())) {
			LOGGER.error("Avatar is already registered");
			throw new InputValidationException("Avatar.userName", avatarRequest.getUserName(), "Avatar is already registered");
		}

		if (!isDobValid(avatarRequest.getDob())) {
			LOGGER.error("Invalid date format for DOB");
			throw new InputValidationException("Avatar.dob", avatarRequest.getDob(),
					"Invalid date format for DOB, use: " + dobFormatData);
		}

		if (!isSsnValid(avatarRequest.getSsn())) {
			LOGGER.error("Invalid format for SSN");
			throw new InputValidationException("Avatar.dob", avatarRequest.getDob(),
					"Invalid format for SSN, use: XXX-XX-XXXX");
		}

		if (isBlackListed(avatarRequest.getDob(), avatarRequest.getSsn())) {
			LOGGER.error("Avatar is blacklisted");
			throw new InputValidationException("Avatar is blacklisted", "", "");
		}

		LOGGER.info("Avatar has been validated successfully");
		return true;
	}

	public static boolean validateRequest(String ssn, String dob) throws InputValidationException {
		if (!isDobValid(dob)) {
			LOGGER.error("Invalid date format for DOB");
			throw new InputValidationException("Avatar.dob", dob,
					"Invalid date format for DOB, use: " + dobFormatData);
		}

		if (!isSsnValid(ssn)) {
			LOGGER.error("Invalid format for SSN");
			throw new InputValidationException("Avatar.dob", ssn,
					"Invalid format for SSN, use: XXX-XX-XXXX");
		}

		if (isBlackListed(dob, ssn)) {
			LOGGER.error("Avatar is blacklisted");
			throw new InputValidationException("Avatar is blacklisted", "", "");
		}

		LOGGER.info("DOB and SSN has been validated successfully");
		return true;
	}

	private static boolean isAvatarDuplicate(String userName) {
		boolean retVal = true;

		if (null != userName && !userName.isEmpty()) {
			// todo: check if repo has this user
			retVal = false;
		}

		return retVal;
	}

	private static boolean isDobValid(String dob) {
		boolean retVal = false;

		if (null != dob) {
			try {
				dobFormat.parse(dob);
				retVal = true;
			} catch (ParseException ex) {
				LOGGER.error("DOB parsing failed: " + ex.getMessage());
			}
		}

		return retVal;
	}

	private static boolean isSsnValid(String ssn) {
		boolean retVal = false;
		if (null != ssn) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(ssn);
			retVal = matcher.matches();
		}

		return retVal;
	}

	private static boolean isBlackListed(String dob, String ssn) {
		boolean retVal = true;

		if (null != dob && null != ssn) {
			
		}
		// todo: check if ssn is blacklisted
		retVal = false;

		return retVal;
	}
}
