package com.opensource.soft.BlogServer.user.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.user.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.user.blog.dao.CollectMapper;
import com.opensource.soft.BlogServer.user.blog.dao.CountMapper;
import com.opensource.soft.BlogServer.user.blog.dao.LikesMapper;
import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.model.Collect;
import com.opensource.soft.BlogServer.user.blog.model.Likes;
import com.opensource.soft.BlogServer.user.blog.service.BlogService;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;

import java.util.Date;
import java.util.List;

@Service(value="blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private LikesMapper likesMapper;
    
    @Autowired
    private CountMapper countMapper;
    
    @Autowired
    private CollectMapper collectMapper;
    
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

	@Override
	public void likeBlog(Likes likes) {
		likes.setUserid(ShiroUser.getUserId());
		likes.setUpdatetime(new Date());
		Likes likesOld = likesMapper.selectByUserIdAndUUID(likes);
		if(likesOld == null){
			likesMapper.insert(likes);
			//喜欢	
			if(likes.getStatus() == 0){
				countMapper.updateLikeAddByUuid(likes.getBloguuid());
			}else{
				countMapper.updateHateAddByUuid(likes.getBloguuid());
			}
		}else{
			likesMapper.updateLikesByKey(likes);
			//开始不喜欢       现在喜欢
			if(likes.getStatus() == 0 && likesOld.getStatus()==1 ){
				countMapper.updateHateMinusByUuid(likes.getBloguuid());
				countMapper.updateLikeAddByUuid(likes.getBloguuid());
			//开始喜欢          现在不喜欢	
			}else if(likes.getStatus() == 1 && likesOld.getStatus()==0){
				countMapper.updateHateAddByUuid(likes.getBloguuid());
				countMapper.updateLikeMinusByUuid(likes.getBloguuid());
			}//其他就是不变不处理
			
		}
		
	}

	@Override
	public void collectBlog(Collect collect) {
		collect.setUserid(ShiroUser.getUserId());
		collect.setCreatetime(new Date());
		collect.setDeleteflag(Boolean.FALSE);
		Collect collectOld = collectMapper.selectByKey(collect);
		if(collectOld == null){
			collectMapper.insert(collect);
			countMapper.updateCollectAddByUuid(collect.getBloguuid());
		}
	}
}
