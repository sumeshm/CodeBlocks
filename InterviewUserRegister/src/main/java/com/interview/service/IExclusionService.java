package com.interview.service;

import com.interview.common.InputValidationException;

public interface IExclusionService {
	/**
	 * Validates a user against a black list using his date of birth and social
	 * security number as identifier.
	 *
	 * @param dob the user's date of birth in ISO 8601 format
	 * @param ssn the user's social security number (United States)
	 * @return true if the user could be validated and is not blacklisted, false if
	 *         the user is blacklisted and therefore failed validation
	 */
	boolean validate(String dob, String ssn) throws InputValidationException;
}
