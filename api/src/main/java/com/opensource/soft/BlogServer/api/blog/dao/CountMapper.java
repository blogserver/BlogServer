package com.opensource.soft.BlogServer.api.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.api.blog.model.Count;

@Component
@Mapper
public interface CountMapper {

	int insert(Count count);

	Count selectByUuid(String bloguuid);

	int updateByUuid(Count count);
	
	int updateVisitByUuid(String bloguuid);
}