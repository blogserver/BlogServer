package com.opensource.soft.BlogServer.page.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.page.blog.model.Blog;

import java.util.List;

@Component
@Mapper
public interface BlogMapper {
	
    List<Blog> findBlogsByStatus(Integer status);
}
