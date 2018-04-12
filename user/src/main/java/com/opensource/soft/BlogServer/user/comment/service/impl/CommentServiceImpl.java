package com.opensource.soft.BlogServer.user.comment.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.user.comment.dao.CommentMapper;
import com.opensource.soft.BlogServer.user.comment.model.Comment;
import com.opensource.soft.BlogServer.user.comment.service.CommentService;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;

@Service(value="commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    
	@Override
	public int save(Comment comment) {
		comment.setDeleteflag(false);
		comment.setCreatetime(new Date());
		comment.setCreateuser(ShiroUser.getUserId());
		commentMapper.insert(comment);
		return 0;
	}
}
