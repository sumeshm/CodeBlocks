package com.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.interview.common.InputValidationException;
import com.interview.model.AvatarEntity;
import com.interview.model.AvatarRequest;
import com.interview.service.IAvatarDaoService;
import com.interview.service.IAvatarService;
import com.interview.service.IExclusionService;
import com.interview.service.util.AvatarValidator;

public class AvatarServiceImpl implements IAvatarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarServiceImpl.class);

	@Autowired
	private IAvatarDaoService avatarDaoService;

	@Autowired
	private IExclusionService exclusionService;

	@Autowired
	private AvatarValidator avatarValidator;

	@Override
	public String createAvatar(AvatarRequest avatarRequest) throws InputValidationException {
		// 1. validate request data
		avatarValidator.validateRequest(avatarRequest);

		// 2. check if SSN-DOB is blacklisted
		boolean isBlacklisted = exclusionService.validate(avatarRequest.getDob(), avatarRequest.getSsn());
		if (isBlacklisted) {
			LOGGER.error("Avatar is blacklisted");
			throw new InputValidationException("Avatar is blacklisted", "", "");
		}

		// 3. check if already registered
		if (isAvatarDuplicate(avatarRequest.getUserName())) {
			LOGGER.error("Avatar is already registered");
			throw new InputValidationException("Avatar.userName", avatarRequest.getUserName(),
					"Avatar is already registered");
		}

		LOGGER.info("Avatar-Request has been accepted");

		// 4. map request to entity
		AvatarEntity avatarEntity = new AvatarEntity();
		avatarEntity.setUserName(avatarRequest.getUserName());
		avatarEntity.setPassword(avatarRequest.getPassword());
		avatarEntity.setDob(avatarRequest.getDob());
		avatarEntity.setSsn(avatarRequest.getSsn());

		// 5. write to DB
		AvatarEntity retVal = avatarDaoService.saveOrUpdate(avatarEntity);
		LOGGER.info("Avatar saved to repo: " + retVal.toString());

		return "http://localhost:8080/avatars/" + retVal.getAvatarId();
	}

	private boolean isAvatarDuplicate(String userName) {
		boolean retVal = false;

		if (null != userName && !userName.isEmpty()) {
			AvatarEntity entity = avatarDaoService.getByName(userName);
			if (null != entity) {
				retVal = true;
			}
		}

		return retVal;
	}
}
