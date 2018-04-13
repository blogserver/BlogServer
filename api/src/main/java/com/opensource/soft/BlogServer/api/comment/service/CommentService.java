package com.opensource.soft.BlogServer.api.comment.service;

import java.util.List;

import com.opensource.soft.BlogServer.api.comment.model.Comment;

public interface CommentService {

	List<Comment> findCommentsByBlogUuid(String blogUuid);

	
}
