package com.opensource.soft.BlogServer.user.user.service;

import com.opensource.soft.BlogServer.user.user.model.Follow;
import com.opensource.soft.BlogServer.user.user.model.User;

public interface UserService {

	User selectByUserId (Integer id);

	User selectByLoginname(String loginname);

	int insert(User user);

	int updateByUserId(User user);

	void followUser(Follow follow);
}
