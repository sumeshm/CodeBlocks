package com.interview.service;

import com.interview.model.AvatarEntity;

public interface IAvatarDaoService {

	/**
	 * Find AvatarEntity with given user-name from repository
	 *
	 * @param userName
	 * @return AvatarEntity
	 */
	public AvatarEntity getByName(String userName);

	/**
	 * Save or Update given AvatarEntity to repository
	 *
	 * @param avatarEntity
	 * @return AvatarEntity
	 */
	public AvatarEntity saveOrUpdate(AvatarEntity avatarEntity);

	/**
	 * Delete AvatarEntity with given user-name from repository
	 *
	 * @param userName
	 */
	public void delete(String userName);
}
