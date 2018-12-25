package com.interview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.model.AvatarEntity;

@Repository
public interface IAvatarRepository extends JpaRepository<AvatarEntity, Long> {
}
