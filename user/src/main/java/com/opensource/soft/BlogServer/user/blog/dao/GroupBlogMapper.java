package com.opensource.soft.BlogServer.user.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.user.blog.model.GroupBlog;

@Component
@Mapper
public interface GroupBlogMapper {

	int deleteByKey(GroupBlog groupBlog);

	int insert(@Param("bloguuid") String bloguuid, @Param("groupids") String... groupid);

	GroupBlog selectByKey(GroupBlog groupBlog);

	List<GroupBlog> selectByBloguuid(String bloguuid);

	List<GroupBlog> selectByGroupid(Integer groupid);

	int updateByKey(GroupBlog groupBlog);
}