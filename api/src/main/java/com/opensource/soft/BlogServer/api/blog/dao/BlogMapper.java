package com.opensource.soft.BlogServer.api.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.api.blog.model.Blog;

@Component
@Mapper
public interface BlogMapper {

	List<Blog> findNewBlogs(Blog blog);

	List<Blog> findNewsByGroupId(Integer groupId);

	List<Blog> findHighVisitBlogs();

	List<Blog> findHightVisitByGroupId(Integer groupId);

}
