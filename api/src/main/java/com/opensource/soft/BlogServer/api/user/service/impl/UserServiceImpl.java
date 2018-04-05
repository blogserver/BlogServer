package com.opensource.soft.BlogServer.api.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.api.user.dao.UserMapper;
import com.opensource.soft.BlogServer.api.user.model.User;
import com.opensource.soft.BlogServer.api.user.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserInfo(Integer userId) {
		return userMapper.selectByUserId(userId);
	}

}
