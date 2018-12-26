package com.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.interview.common.InputValidationException;
import com.interview.dao.AvatarDaoService;
import com.interview.model.AvatarEntity;
import com.interview.model.AvatarRequest;
import com.interview.service.IAvatarService;
import com.interview.service.util.AvatarValidator;

public class AvatarServiceImpl implements IAvatarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarServiceImpl.class);

	@Autowired
	private AvatarDaoService avatarDaoService;

	@Override
	public String createAvatar(AvatarRequest avatarRequest) throws InputValidationException {
		// 1. validate request data
		AvatarValidator.validateRequest(avatarRequest);

		// 2. check if already registered
		if (isAvatarDuplicate(avatarRequest.getUserName())) {
			LOGGER.error("Avatar is already registered");
			throw new InputValidationException("Avatar.userName", avatarRequest.getUserName(),
					"Avatar is already registered");
		}

		LOGGER.info("Avatar-Request has been accepted");

		// 3. map request to entity
		AvatarEntity avatarEntity = new AvatarEntity();
		avatarEntity.setUserName(avatarRequest.getUserName());
		avatarEntity.setPassword(avatarRequest.getPassword());
		avatarEntity.setDob(avatarRequest.getDob());
		avatarEntity.setSsn(avatarRequest.getSsn());

		// 4. write to DB
		AvatarEntity retVal = avatarDaoService.saveOrUpdate(avatarEntity);
		LOGGER.info("Avatar saved to repo: " + retVal.toString());

		return "http://localhost:8080/avatars/" + retVal.getAvatarId();
	}

	private boolean isAvatarDuplicate(String userName) {
		boolean retVal = false;

		if (null != userName && !userName.isEmpty()) {
			AvatarEntity entity = avatarDaoService.getAvatarByName(userName);
			if (null != entity) {
				retVal = true;
			}
		}

		return retVal;
	}
}
