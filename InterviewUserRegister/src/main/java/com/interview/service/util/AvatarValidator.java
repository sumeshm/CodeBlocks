package com.interview.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.interview.common.AppProperties;
import com.interview.common.InputValidationException;
import com.interview.model.AvatarRequest;

@Scope("singleton")
public class AvatarValidator {

	private final Logger LOGGER = LoggerFactory.getLogger(AvatarValidator.class);

	@Autowired
	private AppProperties appProps;

	//private static final String userNameRegex = "\"[a-zA-Z0-9]+\"";
	// "^[a-zA-Z0-9]+$"; 
	// "[A-Za-z0-9]{1,10}"


	/**
	 * @param avatarRequest
	 * @return
	 * @throws InputValidationException
	 */
	public boolean validateRequest(AvatarRequest avatarRequest) throws InputValidationException {

		if (!isUserNameValid(avatarRequest.getUserName())) {
			LOGGER.error("Invalid user-name format");
			throw new InputValidationException("Avatar.userName", avatarRequest.getUserName(),
					"Invalid user-name format, should have at least four characters, alpha-numerical without spaces");
		}

		if (!isPasswordValid(avatarRequest.getPassword())) {
			LOGGER.error("Invalid password format");
			throw new InputValidationException("Avatar.password", avatarRequest.getPassword(),
					"Invalid password format, should have at least four characters, at least one upper case character, at least one number");
		}

		if (!isDobValid(avatarRequest.getDob())) {
			LOGGER.error("Invalid date format for DOB");
			throw new InputValidationException("Avatar.dob", avatarRequest.getDob(),
					"Invalid date format for DOB, use: " + appProps.getDobFormat());
		}

		if (!isSsnValid(avatarRequest.getSsn())) {
			LOGGER.error("Invalid format for SSN");
			throw new InputValidationException("Avatar.dob", avatarRequest.getDob(),
					"Invalid format for SSN, use: XXX-XX-XXXX");
		}

		LOGGER.info("Avatar has been validated successfully");
		return true;
	}

	/**
	 * @param ssn
	 * @param dob
	 * @return
	 * @throws InputValidationException
	 */
	public boolean validateRequest(String ssn, String dob) throws InputValidationException {
		if (!isDobValid(dob)) {
			LOGGER.error("Invalid date format for DOB");
			throw new InputValidationException("Avatar.dob", dob,
					"Invalid date format for DOB, use: " + appProps.getDobFormat());
		}

		if (!isSsnValid(ssn)) {
			LOGGER.error("Invalid format for SSN");
			throw new InputValidationException("Avatar.dob", ssn,
					"Invalid format for SSN, use: XXX-XX-XXXX");
		}

		LOGGER.info("DOB and SSN has been validated successfully");
		return true;
	}

	private boolean isUserNameValid(String userName) throws InputValidationException {
		// alpha-numerical without spaces
		// return userName.matches(userNameRegex);

		// ASCII range HEX values
		// 0 --> x30 to 9 --> x39
		// A --> x41 to Z --> x5A
		// a --> x61 to z --> x7A
		boolean isAlpha = false;
		boolean isNumeric = false;
		if (null != userName && userName.length() >= appProps.getUserNameMinLength() && userName.length() <= appProps.getUserNameMaxLength()) {
			for (int count = 0; count < userName.length() && (!isNumeric || !isAlpha); count++) {
				int testChar = userName.charAt(count);

				// char is in one of the following ranges
				// {0 to 9}  OR  {A to Z}  OR  {a to z}
				if (!isNumeric && (testChar >= 0x30 && testChar <= 0x39)) {
					isNumeric = true;
				}
				if (!isAlpha) {
					if ( (testChar >= 0x41 && testChar <= 0x5A) || (testChar >= 0x61 && testChar <= 0x7A)) {
						isAlpha = true;
					}
				}
			}
		}

		return (isNumeric & isAlpha);
	}

	private boolean isPasswordValid(String password) throws InputValidationException {
		// password (at least four characters, at least one upper case character, at least one number)

		// ASCII range HEX values
		// 0 --> x30 to 9 --> x39
		// A --> x41 to Z --> x5A
		// a --> x61 to z --> x7A

		boolean isAlpha = false;
		boolean isUpperCase = false;
		boolean isNumeric = false;
		if (null != password && password.length() >= appProps.getPasswordMinLength() && password.length() <= appProps.getPasswordMaxLength()) {

			for (int count = 0; count < password.length() && (!isNumeric || !isAlpha || !isUpperCase); count++) {
				int testChar = password.charAt(count);

				// char is in one of the following ranges
				// {0 to 9}  OR  {A to Z}  OR  {a to z}
				if (!isNumeric && (testChar >= 0x30 && testChar <= 0x39)) {
					isNumeric = true;
				}
				if (!isUpperCase && testChar >= 0x41 && testChar <= 0x5A) {
					isUpperCase = true;
				}
				if (!isAlpha && testChar >= 0x61 && testChar <= 0x7A) {
						isAlpha = true;
				}
			}
		}

		return (isNumeric & isAlpha & isUpperCase);
	}

	private boolean isDobValid(String dob) {
		boolean retVal = false;

		if (null != dob) {
			try {
				SimpleDateFormat dobFormat = new SimpleDateFormat(appProps.getDobFormat());
				dobFormat.parse(dob);
				retVal = true;
			} catch (ParseException ex) {
				LOGGER.error("DOB parsing failed: " + ex.getMessage());
			}
		}

		return retVal;
	}

	private boolean isSsnValid(String ssn) {
		boolean retVal = false;
		if (null != ssn) {
			Pattern pattern = Pattern.compile(appProps.getSsnRegex());
			Matcher matcher = pattern.matcher(ssn);
			retVal = matcher.matches();
		}

		return retVal;
	}
}
