package com.opensource.soft.BlogServer.user.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.user.blog.model.Blog;

import java.util.List;

@Component
@Mapper
public interface BlogMapper {
	
    int save(Blog blog);

    List<Blog> findMyBlogs(Integer userId);
}
