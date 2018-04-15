package com.opensource.soft.BlogServer.api.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.api.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.api.blog.dao.CountMapper;
import com.opensource.soft.BlogServer.api.blog.dao.VisitMapper;
import com.opensource.soft.BlogServer.api.blog.model.Blog;
import com.opensource.soft.BlogServer.api.blog.model.Count;
import com.opensource.soft.BlogServer.api.blog.model.Visit;
import com.opensource.soft.BlogServer.api.blog.service.BlogService;
import com.opensource.soft.BlogServer.common.Constant;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired
	private VisitMapper visitMapper;
	
	@Autowired
	private CountMapper countMapper;
	
	
	@Override
	public List<Blog> findNewBlogs() {
		Blog blog = new Blog();
		blog.setStatus(Constant.BLOGSTATUS.RELEASE_SUCCESS);
		return this.blogMapper.findNewBlogs(blog);
	}

	@Override
	public void visitHistory(Visit visit) {
		
		String url = visit.getUrl();
		visit.setBlogid(url.substring(url.lastIndexOf("/")+1, url.lastIndexOf(".")));
		visit.setCreatetime(new Date());
		this.visitMapper.insert(visit);
		
		if(countMapper.selectByUuid(visit.getBlogid())==null) {
			Count count = new Count();
			count.setBloguuid(visit.getBlogid());
			count.setUserid(visit.getUserid());
			count.setVisit(1);
			count.setHate(0);
			count.setLike(0);
			count.setCollect(0);
			countMapper.insert(count);
		}else {
			countMapper.updateVisitByUuid(visit.getBlogid());
		}
		
	}

	@Override
	public Count findBlogCount(String blogUuid) {
		return countMapper.selectByUuid(blogUuid);
	}

	@Override
	public List<Blog> findByGroupId(Integer groupId) {
		return this.blogMapper.findByGroupId(groupId) ;
	}

}
