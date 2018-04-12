package com.opensource.soft.BlogServer.user.comment.dao;

import com.opensource.soft.BlogServer.user.comment.model.Comment;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CommentMapper {

    int insert(Comment comment);
    
    Comment selectByKey(Integer id);

    List<Comment> selectByBlogUuid(String bloguuid);

    int updateByKey(Comment record);
}