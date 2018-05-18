package com.opensource.soft.BlogServer.user.blog.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.common.util.PathUtil;
import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.model.Collect;
import com.opensource.soft.BlogServer.user.blog.model.Likes;
import com.opensource.soft.BlogServer.user.blog.service.BlogService;
import com.opensource.soft.BlogServer.user.common.property.UserProperties;

@Controller
@RequestMapping(value="/blog")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private UserProperties userProperties;
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String home(Model model) {
    	model.addAttribute("staticUrl", userProperties.getStaticUrl());
        return "blog/create";
    }

    @ResponseBody
    @RequestMapping(value="/saveBlog", method = RequestMethod.POST)
    public String saveBlog(Blog blog,String groupIds){
        logger.info(blog.toString());
        blogService.save(blog,groupIds);
        return BaseResponse.successJson();
    }

    @RequestMapping(value="/myBlog", method = RequestMethod.GET)
    public String findMyBlog(@RequestParam(required=false,defaultValue="1")Integer pageNum, @RequestParam(required=false,defaultValue="10")Integer pageSize,Model model){
        model.addAttribute("myblog", blogService.findMyBlog(pageNum,pageSize));
        model.addAttribute("url", userProperties.getStaticUrl());
        return "blog/myBlog";
    }
    
    // 单文件上传
 	@RequestMapping(value = "/upload", method = RequestMethod.POST)
 	@ResponseBody
 	public String upload(@RequestParam("file") MultipartFile file) {

 		try {
 			if (file.isEmpty()) {
 				return "{\"success\":\"" + false + "\"}";
 			}
 			// 获得文件后缀名
 			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
 			String path = "img" + File.separator + PathUtil.imgPath() + suffix;
 			// 设置文件存储路径
 			File dest = new File(userProperties.getWebSite() + path);
 			// 检测是否存在目录
 			if (!dest.getParentFile().exists()) {
 				dest.getParentFile().mkdirs();// 新建文件夹
 			}
 			file.transferTo(dest);// 文件写入

 			String urlPath = path.replace("\\", "/");
 			return "{\"success\":\"" + true + "\",\"file_path\":\""+userProperties.getStaticUrl()+"/" + urlPath + "\"}";
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		return "{\"success\":\"" + false + "\"}";
 	}


    @ResponseBody
    @RequestMapping(value="/likeBlog", method = RequestMethod.POST)
    public String likeBlog(Likes likes){
        blogService.likeBlog(likes);
        return BaseResponse.successJson();
    }

    @ResponseBody
    @RequestMapping(value="/collectBlog", method = RequestMethod.POST)
    public String collectBlog(Collect collect){
        blogService.collectBlog(collect);
        return BaseResponse.successJson();
    }

    @RequestMapping(value="/myCollect", method = RequestMethod.GET)
    public String findMyCollect(@RequestParam(required=false,defaultValue="1")Integer pageNum, @RequestParam(required=false,defaultValue="10")Integer pageSize,Model model){
        model.addAttribute("myCollect", blogService.findMyCollect(pageNum,pageSize));
        model.addAttribute("url", userProperties.getStaticUrl());
        return "blog/myCollect";
    }
}
