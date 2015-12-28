package com.lm.mapping;

import java.util.List;

import com.lm.entity.UserEntity;

public interface UserMapper {
	UserEntity getUserEntityByEmail(String email);
	
	UserEntity getUserEntityById(int userId);
	
	List<UserEntity> getUserEntities();
	
	int insertUser(UserEntity userEntity);
}
