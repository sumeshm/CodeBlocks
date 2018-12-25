package com.interview.service.impl;

import com.interview.common.InputValidationException;
import com.interview.service.IExclusionService;
import com.interview.service.util.AvatarValidator;

public class ExclusionServiceImpl implements IExclusionService {

	@Override
	public boolean validate(String dob, String ssn) throws InputValidationException {
		return AvatarValidator.validateRequest(ssn, dob);
	}
}
