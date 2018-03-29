package com.opensource.soft.BlogServer.api.blog.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping("/blog")
public class BlogController{
	
	@RequestMapping(value = "/{blogUuId}" , method = RequestMethod.GET)
	public String findBlog(@PathVariable String blogUuId, @RequestParam(name = "version" ,required = false)Integer version) {
		return BaseResponse.successJson();
	}
	
}
