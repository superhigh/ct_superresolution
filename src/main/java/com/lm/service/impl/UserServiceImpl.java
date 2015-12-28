package com.lm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lm.entity.UserEntity;
import com.lm.mapping.UserMapper;
import com.lm.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	


	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserEntity getUserEntityById(int userId) {
		return this.userMapper.getUserEntityById(userId);
	}

	@Override
	public List<UserEntity> getUserEntities() {
		return this.userMapper.getUserEntities();
	}

	@Override
	public UserEntity insertUserEntity(UserEntity userEntity) {
		this.userMapper.insertUser(userEntity);

		return getUserEntityById(userEntity.getId());
	}

	@Override
	public UserEntity getUserEntityByEmail(String email) {
		return this.userMapper.getUserEntityByEmail(email);
	}

}
