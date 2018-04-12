package com.opensource.soft.BlogServer.user.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.user.blog.model.Count;

@Component
@Mapper
public interface CountMapper {

	int insert(Count count);

	Count selectByUuid(String bloguuid);

	int updateByUuid(Count count);
	
	int updateLikeAddByUuid(String bloguuid);
	int updateLikeMinusByUuid(String bloguuid);
	int updateHateAddByUuid(String bloguuid);
	int updateHateMinusByUuid(String bloguuid);
	
	int updateCollectAddByUuid(String bloguuid);
}