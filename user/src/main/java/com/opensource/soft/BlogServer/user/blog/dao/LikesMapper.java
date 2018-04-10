package com.opensource.soft.BlogServer.user.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.user.blog.model.Likes;

@Component
@Mapper
public interface LikesMapper {

	int insert(Likes likes);

	Likes selectByUserIdAndUUID(Likes likes);

	int updateLikesByKey(Likes likes);

}