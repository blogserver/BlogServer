package com.opensource.soft.BlogServer.api.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.api.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.api.blog.model.Blog;
import com.opensource.soft.BlogServer.api.blog.service.BlogService;
import com.opensource.soft.BlogServer.common.Constant;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogMapper blogMapper;
	
	@Override
	public List<Blog> findNewBlogs() {
		Blog blog = new Blog();
		blog.setStatus(Constant.BLOGSTATUS.RELEASE_SUCCESS);
		return this.blogMapper.findNewBlogs(blog);
	}

}
