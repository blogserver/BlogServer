package com.opensource.soft.BlogServer.user.comment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.user.comment.model.Comment;
import com.opensource.soft.BlogServer.user.comment.service.CommentService;

@Controller
@RequestMapping(value="/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    
    @Autowired
    private CommentService commentService;
    
    @ResponseBody
    @RequestMapping(value="/saveComment", method = RequestMethod.POST)
    public String saveComment(Comment comment){
        logger.info(comment.toString());
        commentService.save(comment);
        return BaseResponse.successJson();
    }

}
