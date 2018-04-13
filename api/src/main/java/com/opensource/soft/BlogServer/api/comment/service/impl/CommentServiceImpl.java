package com.opensource.soft.BlogServer.api.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.api.comment.dao.CommentMapper;
import com.opensource.soft.BlogServer.api.comment.model.Comment;
import com.opensource.soft.BlogServer.api.comment.service.CommentService;


@Service(value="commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

	@Override
	public List<Comment> findCommentsByBlogUuid(String blogUuid) {
		return commentMapper.selectByBlogUuid(blogUuid);
	}
    
}
