package com.interview.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.model.AvatarEntity;

@Service
public class AvatarDaoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvatarDaoService.class);

	private Map<String, Long> avatarMap = new HashMap<>();

	@Autowired
	private IAvatarRepository avatarRepo;

	@PostConstruct
	private void init() {
		avatarRepo.findAll().forEach(avatarEntity -> avatarMap.put(avatarEntity.getUserName(), avatarEntity.getAvatarId()));
		LOGGER.info("MAP: " + avatarMap.toString());
	}

	public List<AvatarEntity> getAllAvatars() {
		List<AvatarEntity> avatarEntityList = new ArrayList<AvatarEntity>();
		avatarRepo.findAll().forEach(avatarEntity -> avatarEntityList.add(avatarEntity));
		return avatarEntityList;
	}

	public AvatarEntity getAvatarByName(String userName) {
		Long id = avatarMap.get(userName);
		if (null != id) {
			return avatarRepo.getOne(id);
		}

		return null;
	}

	public AvatarEntity saveOrUpdate(AvatarEntity avatarEntity) {
		AvatarEntity retVal = avatarRepo.save(avatarEntity);
		avatarMap.put(retVal.getUserName(), retVal.getAvatarId());
		LOGGER.info("MAP: " + avatarMap.toString());

		return retVal;
	}

	public void delete(String userName) {
		Long id = avatarMap.get(userName);
		if (null != id) {
			avatarMap.remove(userName);
			avatarRepo.deleteById(id);
		}
	}
}
