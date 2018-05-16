package com.opensource.soft.BlogServer.api.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.api.blog.model.Visit;
import com.opensource.soft.BlogServer.api.blog.service.BlogService;
import com.opensource.soft.BlogServer.api.common.http.IpAddress;
import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	/**
	 * 获取最新的blog内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String findNewBlogs() {
		return BaseResponse.successJson(blogService.findNewBlogs());
	}

	/**
	 * 获取访问量最高的blog内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/highVisit", method = RequestMethod.GET)
	public String findHighVisitBlogs() {
		return BaseResponse.successJson(blogService.findHighVisitBlogs());
	}

	
	/**
	 * blog访问 记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/visit", method = RequestMethod.POST)
	public String visit(HttpServletRequest request, Visit visit) {
		visit.setIp(IpAddress.getRemoteAddress(request));
		blogService.visitHistory(visit);
		return BaseResponse.successJson();
	}
	
	/**
	 * 获取博客统计数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public String findBlogCount(String blogUuid) {
		return BaseResponse.successJson(blogService.findBlogCount(blogUuid));
	}
	
	@RequestMapping(value = "/findNewsByGroupId", method = RequestMethod.GET)
	public String findNewsByGroupId(Integer groupId) {
		return BaseResponse.successJson(blogService.findNewsByGroupId(groupId));
	}
	
	@RequestMapping(value = "/findHightVisitByGroupId", method = RequestMethod.GET)
	public String findHightVisitByGroupId(Integer groupId) {
		return BaseResponse.successJson(blogService.findHightVisitByGroupId(groupId));
	}
	
}
