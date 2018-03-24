package com.opensource.soft.BlogServer.user.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.user.user.model.User;

@Component
@Mapper
public interface UserMapper {

	User selectByUserId (Integer id);

	User selectByLoginname(String loginname);

	int deleteByUserId(Integer id);

	int insert(User user);
	
	int updateByUserId(User user);

}
