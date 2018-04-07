package com.opensource.soft.BlogServer.user.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.user.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.service.BlogService;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;

import java.util.Date;
import java.util.List;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int save(Blog blog) {
        blog.setDeleteflag(false);
        blog.setVersion(1);
        blog.setCreateuser(ShiroUser.getUserId());
        blog.setCreatetime(new Date());
        blog.setUpdatetime(new Date());
        return blogMapper.save(blog);
    }

    @Override
    public List<Blog> findMyBlog() {
        return blogMapper.findMyBlogs(ShiroUser.getUserId());
    }
}
