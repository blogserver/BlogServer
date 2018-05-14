package com.opensource.soft.BlogServer.user.blog.service;


import com.github.pagehelper.PageInfo;
import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.model.Collect;
import com.opensource.soft.BlogServer.user.blog.model.Likes;

public interface BlogService {
	
    int save(Blog blog, String groupIds);

    PageInfo<Blog> findMyBlog(Integer pageNum, Integer pageSize);

	void likeBlog(Likes likes);

	void collectBlog(Collect collect);
}
