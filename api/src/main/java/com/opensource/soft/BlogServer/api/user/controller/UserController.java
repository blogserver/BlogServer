package com.opensource.soft.BlogServer.api.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.api.user.service.UserService;
import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/info" , method = RequestMethod.GET)
	public String findUserInfo(Integer userId) {
		logger.info("获取用户信息 {}",userId);
		return BaseResponse.successJson(userService.findUserInfo(userId));
	}
	
}
