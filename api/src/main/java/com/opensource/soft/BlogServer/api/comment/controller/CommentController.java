package com.opensource.soft.BlogServer.api.comment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.api.comment.service.CommentService;
import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping(value="/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    
    @Autowired
    private CommentService commentService;
    
    /**
     * 查询blog的评论
     * @return
     */
    @RequestMapping(value = "/{blogUuid}", method = RequestMethod.GET)
    public String findCommentsByBlogUuid(@PathVariable String blogUuid){
    	logger.info("获取blog {} 的评论",blogUuid);
    	return BaseResponse.successJson(commentService.findCommentsByBlogUuid(blogUuid));
    }

}
