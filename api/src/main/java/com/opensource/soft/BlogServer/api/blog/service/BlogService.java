package com.opensource.soft.BlogServer.api.blog.service;

import java.util.List;

import com.opensource.soft.BlogServer.api.blog.model.Blog;
import com.opensource.soft.BlogServer.api.blog.model.Count;
import com.opensource.soft.BlogServer.api.blog.model.Visit;

public interface BlogService {

	List<Blog> findNewBlogs();

	void visitHistory(Visit visit);

	Count findBlogCount(String blogUuid);

	List<Blog> findNewsByGroupId(Integer groupId);

	List<Blog> findHighVisitBlogs();

	List<Blog> findHightVisitByGroupId(Integer groupId);
}
