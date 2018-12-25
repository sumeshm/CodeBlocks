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
		LOGGER.info("Avatar has been validated");

		AvatarValidator.validateRequest(avatarRequest);

		AvatarEntity avatarEntity = new AvatarEntity();
		avatarEntity.setUserName(avatarRequest.getUserName());
		avatarEntity.setPassword(avatarRequest.getPassword());
		avatarEntity.setDob(avatarRequest.getDob());
		avatarEntity.setSsn(avatarRequest.getSsn());

		AvatarEntity retVal = avatarDaoService.saveOrUpdate(avatarEntity);
		LOGGER.info("Avatar saved to repo: " + retVal.toString());

		LOGGER.info("All Avatars -> {}", avatarDaoService.getAllAvatars());

		//		return "http://localhost:8080/avatars/" + avatar.getUserId();
		return "DONE";
	}
}
