package com.interview.service;

import com.interview.common.InputValidationException;
import com.interview.model.AvatarRequest;

public interface IRegistrationService {

	/**
	 * Register the new Avatar, pending validations
	 *
	 * @param avatar - AvatarRequest with details like: userName, password, dob and ssn
	 * @return URL to access Avatar
	 * @throws InputValidationException, if Avatar is already registered OR if validation of Avatar's dob, ssn fails
	 */
	public String createAvatar(AvatarRequest avatarRequest) throws InputValidationException;
}
