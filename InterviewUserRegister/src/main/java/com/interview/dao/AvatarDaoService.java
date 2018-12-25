package com.interview.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.model.AvatarEntity;

@Service
public class AvatarDaoService {

	@Autowired
	private IAvatarRepository avatarRepo;

	public List<AvatarEntity> getAllAvatars() {
		List<AvatarEntity> avatarEntityList = new ArrayList<AvatarEntity>();
		avatarRepo.findAll().forEach(avatarEntity -> avatarEntityList.add(avatarEntity));
		return avatarEntityList;
	}

	public AvatarEntity getAvatarEntityById(Long avatarId) {
		return avatarRepo.findById(avatarId).get();
	}

	public AvatarEntity saveOrUpdate(AvatarEntity avatarEntity) {
		return avatarRepo.save(avatarEntity);
	}

	public void delete(Long avatarId) {
		avatarRepo.deleteById(avatarId);
	}
}
