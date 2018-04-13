package com.opensource.soft.BlogServer.api.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.opensource.soft.BlogServer.api.comment.model.Comment;

@Component
@Mapper
public interface CommentMapper {

    Comment selectByKey(Integer id);

    List<Comment> selectByBlogUuid(String bloguuid);

}