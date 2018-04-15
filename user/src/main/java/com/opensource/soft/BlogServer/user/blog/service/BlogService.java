package com.opensource.soft.BlogServer.user.blog.service;


import java.util.List;

import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.model.Collect;
import com.opensource.soft.BlogServer.user.blog.model.Likes;

public interface BlogService {
	
    int save(Blog blog, String groupIds);

    List<Blog> findMyBlog();

	void likeBlog(Likes likes);

	void collectBlog(Collect collect);
}
