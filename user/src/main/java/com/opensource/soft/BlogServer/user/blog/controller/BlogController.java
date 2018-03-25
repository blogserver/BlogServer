package com.opensource.soft.BlogServer.user.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.service.BlogService;

@Controller
@RequestMapping(value="/blog")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogService blogService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        return "blog/createblog";
    }

    @ResponseBody
    @RequestMapping(value="/saveBlog", method = RequestMethod.POST)
    public String saveBlog(Blog blog){
        logger.info(blog.toString());
        blogService.save(blog);
        return BaseResponse.successJson();
    }

    @ResponseBody
    @RequestMapping(value="/findMyBlog", method = RequestMethod.GET)
    public String findMyBlog(){
        return BaseResponse.successJson( blogService.findMyBlog());
    }

}
