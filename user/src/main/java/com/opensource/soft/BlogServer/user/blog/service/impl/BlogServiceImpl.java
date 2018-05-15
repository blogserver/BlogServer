package com.opensource.soft.BlogServer.user.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opensource.soft.BlogServer.common.util.HttpsUtil;
import com.opensource.soft.BlogServer.user.blog.dao.BlogMapper;
import com.opensource.soft.BlogServer.user.blog.dao.CollectMapper;
import com.opensource.soft.BlogServer.user.blog.dao.CountMapper;
import com.opensource.soft.BlogServer.user.blog.dao.GroupBlogMapper;
import com.opensource.soft.BlogServer.user.blog.dao.LikesMapper;
import com.opensource.soft.BlogServer.user.blog.model.Blog;
import com.opensource.soft.BlogServer.user.blog.model.Collect;
import com.opensource.soft.BlogServer.user.blog.model.Likes;
import com.opensource.soft.BlogServer.user.blog.service.BlogService;
import com.opensource.soft.BlogServer.user.common.property.UserProperties;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    @Autowired
    private GroupBlogMapper groupBlogMapper;
    
    @Autowired
    private UserProperties userProperties;
    
    @Override
    public int save(Blog blog, String groupIds) {
        blog.setDeleteflag(false);
        blog.setVersion(1);
        blog.setCreateuser(ShiroUser.getUserId());
        blog.setCreatetime(new Date());
        blog.setUpdatetime(new Date());
        groupBlogMapper.insert(blog.getUuid(), groupIds.split(","));
        blogMapper.save(blog);
        createPage(blog.getUuid());
        return 0;
    }

    /**
     * 生成页面
     * @param uuid
     */
    private void createPage(String uuid) {
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("uuid", uuid);
		String url = userProperties.getPageServerUrl()+"/page/createPage";
		String result = null;
		try {
			result= HttpsUtil.doPostJson(url, params);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
    }
    
    @Override
    public PageInfo<Blog> findMyBlog(Integer pageNum, Integer pageSize) {
    	//获取第1页，10条内容，默认查询总数count
    	PageHelper.startPage(pageNum, pageSize);
    	//用PageInfo对结果进行包装
    	List<Blog> list =  blogMapper.findMyBlogs(ShiroUser.getUserId());
    	return new PageInfo<Blog>(list);
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

	@Override
	public PageInfo<Blog> findMyCollect(Integer pageNum, Integer pageSize) {
		//获取第1页，10条内容，默认查询总数count
    	PageHelper.startPage(pageNum, pageSize);
    	//用PageInfo对结果进行包装
    	List<Blog> list =  blogMapper.findMyCollects(ShiroUser.getUserId());
    	return new PageInfo<Blog>(list);
	}
}
