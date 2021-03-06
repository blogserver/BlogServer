package com.opensource.soft.BlogServer.page.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.page.blog.service.BlogService;
import com.opensource.soft.BlogServer.page.common.freemarker.FreemakerHelper;

@Controller
@RequestMapping(value = "/page")
public class BlogController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	private BlogService blogService;
	
    @ResponseBody
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String createIndex(){
        //生成页面
        Map<String, Object> dataSource = new HashMap<String, Object>();
        dataSource.put("blog", "");
    	FreemakerHelper.createHtml("index", dataSource, "index.html");
        return BaseResponse.successJson();
    }
	
    @ResponseBody
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String createSearch(){
        //生成页面
        Map<String, Object> dataSource = new HashMap<String, Object>();
        dataSource.put("blog", "");
    	FreemakerHelper.createHtml("search", dataSource, "search.html");
        return BaseResponse.successJson();
    }
    
    @ResponseBody
    @RequestMapping(value="/group", method = RequestMethod.GET)
    public String createGroup(){
        //生成页面
        Map<String, Object> dataSource = new HashMap<String, Object>();
        dataSource.put("blog", "");
    	FreemakerHelper.createHtml("group", dataSource, "group.html");
        return BaseResponse.successJson();
    }
    
    
    @ResponseBody
    @RequestMapping(value="/groupdetail", method = RequestMethod.GET)
    public String createGroupDetail(){
        //生成页面
        Map<String, Object> dataSource = new HashMap<String, Object>();
        dataSource.put("blog", "");
    	FreemakerHelper.createHtml("groupdetail", dataSource, "groupdetail.html");
        return BaseResponse.successJson();
    }
	
	@ResponseBody
	@RequestMapping(value = "/createPage", method = RequestMethod.POST)
	public String createPage(@RequestBody String data) {
		logger.info("接收请求数据 {}",data);
		String uuid = (String) JSONObject.parseObject(data).get("uuid");
		this.blogService.createPage(uuid);
		return BaseResponse.successJson();
	}


}
