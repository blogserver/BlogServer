package com.opensource.soft.BlogServer.page.blog.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.common.Constant;
import com.opensource.soft.BlogServer.common.util.PathUtil;
import com.opensource.soft.BlogServer.page.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.page.blog.model.Blog;
import com.opensource.soft.BlogServer.page.blog.service.BlogService;
import com.opensource.soft.BlogServer.page.common.freemarker.FreemakerHelper;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    
	@Override
	public boolean createPage(String uuid) {

		Blog blog = blogMapper.findBlogByUuid(uuid);
		if(blog.getStatus() == Constant.BLOGSTATUS.START_RELEASE) {
			Blog b = new Blog();
			b.setUuid(blog.getUuid());
			b.setStatus(Constant.BLOGSTATUS.START_PROGRESS);
			b.setUpdatetime(new Date());
			blogMapper.updateBlogStatusByUuid(b);
	        String location ="blog" +File.separator+ PathUtil.htmlPath()+ File.separator+blog.getUuid()+".html";
	        //生成页面
	        Map<String, Object> dataSource = new HashMap<String, Object>();
	        dataSource.put("blog", blog);
	        boolean isTrue = FreemakerHelper.createHtml("detail", dataSource, location);
	        if(isTrue) {
	        	b.setStatus(Constant.BLOGSTATUS.RELEASE_SUCCESS);
	        	b.setLocation(location.replace(File.separator, "\\"));
	        }else{
	        	b.setStatus(Constant.BLOGSTATUS.RELEASE_ERROR);
	        }
	        b.setUpdatetime(new Date());
			blogMapper.updateBlogStatusByUuid(b);
		}
		return false;
	}
	
    @Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
    public void work() throws Exception {
    	
    	List<Blog> blogList = blogMapper.findBlogsByStatus(Constant.BLOGSTATUS.START_RELEASE);
    	for (Blog blog : blogList) {
    		createPage(blog.getUuid());
		}
        System.out.println("执行调度任务："+new Date());
    }

}
