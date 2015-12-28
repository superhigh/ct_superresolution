package com.lm.service;

import java.util.List;

import com.lm.entity.UserEntity;


public interface UserService {
	UserEntity getUserEntityByEmail(String email);
	
	UserEntity getUserEntityById(int userId);
	
	List<UserEntity> getUserEntities();
	
	UserEntity insertUserEntity(UserEntity userEntity);
}
