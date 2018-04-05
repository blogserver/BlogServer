package com.opensource.soft.BlogServer.api.blog.service;

import java.util.List;

import com.opensource.soft.BlogServer.api.blog.model.Blog;

public interface BlogService {

	List<Blog> findNewBlogs();

}
