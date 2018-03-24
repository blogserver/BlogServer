package com.opensource.soft.BlogServer.user.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.user.common.shiro.PasswordHelper;
import com.opensource.soft.BlogServer.user.user.dao.UserMapper;
import com.opensource.soft.BlogServer.user.user.model.User;
import com.opensource.soft.BlogServer.user.user.service.UserService;

import java.util.Date;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Override
	public User selectByUserId(Integer userId) {
		return this.userMapper.selectByUserId(userId);
	}

	@Override
	public int insert(User user) {
		passwordHelper.encryptPassword(user);
		user.setStatus(0);
		if(user.getNickname() == null || user.getNickname().trim().isEmpty()){
			user.setNickname(user.getLoginname());
		}
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		return this.userMapper.insert(user);
	}

	@Override
	public int updateByUserId(User user) {
		return this.userMapper.updateByUserId(user);
	}

	@Override
	public User selectByLoginname(String loginname) {
		return this.userMapper.selectByLoginname(loginname);
	}
	
	
}
