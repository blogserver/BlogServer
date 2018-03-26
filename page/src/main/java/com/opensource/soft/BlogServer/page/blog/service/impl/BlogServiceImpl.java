package com.opensource.soft.BlogServer.page.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.common.Constant;
import com.opensource.soft.BlogServer.page.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.page.blog.model.Blog;
import com.opensource.soft.BlogServer.page.blog.service.BlogService;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

	@Override
	public boolean createPage(Integer pageId) {
		// TODO Auto-generated method stub
		return false;
	}
	
    @Scheduled(cron = "0 0/3 * * * ?") // 每分钟执行一次
    public void work() throws Exception {
    	List<Blog> blogList = blogMapper.findBlogsByStatus(Constant.BLOGSTATUS.START_RELEASE);
    	for (Blog blog : blogList) {
			
		}
    	
        System.out.println("执行调度任务："+new Date());
    }
}
