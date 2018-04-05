package com.opensource.soft.BlogServer.api.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.api.user.model.User;

@Component
@Mapper
public interface UserMapper {

	User selectByUserId (Integer id);

}
