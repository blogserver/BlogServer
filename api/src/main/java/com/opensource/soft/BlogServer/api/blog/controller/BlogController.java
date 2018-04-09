package com.opensource.soft.BlogServer.api.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.api.blog.model.Visit;
import com.opensource.soft.BlogServer.api.blog.service.BlogService;
import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping("/blog")
public class BlogController{
	
	@Autowired
	private BlogService blogService;
	
	/**
	 * 获取最新的blog内容
	 * @return
	 */
	@RequestMapping(value = "/news" , method = RequestMethod.GET)
	public String findNewBlogs() {
		return BaseResponse.successJson(blogService.findNewBlogs());
	}
	
	/**
	 * blog访问 记录
	 * @return
	 */
	@RequestMapping(value = "/visit" , method = RequestMethod.POST)
	public String visit(HttpServletRequest request,Visit visit) {
		blogService.visitHistory(visit);
		return BaseResponse.successJson();
	}
	
}
