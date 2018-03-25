package com.opensource.soft.BlogServer.user.blog.service;


import java.util.List;

import com.opensource.soft.BlogServer.user.blog.model.Blog;

public interface BlogService {
	
    int save(Blog blog);

    List<Blog> findMyBlog();
}
