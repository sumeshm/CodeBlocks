package com.interview.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.model.AvatarEntity;
import com.interview.service.IAvatarDaoService;

@Service
public class AvatarDaoService implements IAvatarDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarDaoService.class);

	// using Map as a cache for faster lookup
	// store <Avatar.userName, Avatar.id>
	private Map<String, Long> avatarMap = new HashMap<>();

	@Autowired
	private IAvatarRepository avatarRepo;

	@PostConstruct
	private void init() {
		// this can be used to restore DB persistence, if any
		avatarRepo.findAll().forEach(avatarEntity -> avatarMap.put(avatarEntity.getUserName(), avatarEntity.getAvatarId()));
		LOGGER.info("Avatar has been added from soted persitance");
		LOGGER.info(" --> " + avatarMap.toString());
	}

	@Override
	public AvatarEntity getByName(String userName) {
		LOGGER.info("Avatar map size=" + avatarMap.size());
		Long id = avatarMap.get(userName);
		if (null != id) {
			LOGGER.info("Avatar has been found");
			return avatarRepo.getOne(id);
		}

		return null;
	}

	@Override
	public AvatarEntity saveOrUpdate(AvatarEntity avatarEntity) {
		LOGGER.info("Avatar map size(start)=" + avatarMap.size());
		AvatarEntity retVal = avatarRepo.save(avatarEntity);
		avatarMap.put(retVal.getUserName(), retVal.getAvatarId());

		LOGGER.info("Avatar map size(end)=" + avatarMap.size());
		LOGGER.info(" --> " + avatarMap.toString());

		return retVal;
	}

	@Override
	public void delete(String userName) {
		LOGGER.info("Avatar map size(start)=" + avatarMap.size());
		Long id = avatarMap.get(userName);
		if (null != id) {
			avatarMap.remove(userName);
			avatarRepo.deleteById(id);
			LOGGER.info("Avatar has been removed");
		}
		LOGGER.info("Avatar map size(end)=" + avatarMap.size());
	}
}
