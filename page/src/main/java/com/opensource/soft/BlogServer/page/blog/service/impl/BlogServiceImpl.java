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
import com.opensource.soft.BlogServer.page.common.property.PageProperties;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    
    @Autowired
    private PageProperties page;

	@Override
	public boolean createPage(String uuid) {

		Blog blog = blogMapper.findBlogByUuid(uuid);
		if(blog.getStatus() == Constant.BLOGSTATUS.START_RELEASE) {
			Blog b = new Blog();
			b.setUuid(blog.getUuid());
			b.setStatus(Constant.BLOGSTATUS.START_PROGRESS);
			blogMapper.updateBlogStatusByUuid(b);
			
			System.out.println("生成页面");
			
		}
		return false;
	}
	
    @Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
    public void work() throws Exception {
    	
    	System.out.println(page.getWebSite()+page.getWebUrl()+page.isCacheftl());
    	List<Blog> blogList = blogMapper.findBlogsByStatus(Constant.BLOGSTATUS.START_RELEASE);
    	for (Blog blog : blogList) {
    		createPage(blog.getUuid());
		}
    	
        System.out.println("执行调度任务："+new Date());
    }

}
