package com.opensource.soft.BlogServer.user.group.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;
import com.opensource.soft.BlogServer.user.group.data.Group;
import com.opensource.soft.BlogServer.user.group.mapper.GroupMapper;
import com.opensource.soft.BlogServer.user.group.service.GroupService;

@Service(value="groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public int save(Group group) {
		if(group.getId()==null) {
			group.setStatus(0);
			group.setCreatetime(new Date());
			group.setUpdatetime(new Date());
			group.setDeleteflag(false);
			group.setCreateuser(ShiroUser.getUserId());
			return groupMapper.insert(group);
		}else {
			group.setStatus(0);
			group.setUpdatetime(new Date());
			group.setDeleteflag(false);
			return groupMapper.updateById(group);
		}
	}

	@Override
	public List<Group> findAllGroup() {
		return groupMapper.selectAllGroup();
	}

	@Override
	public PageInfo<Group> findMyGroup(Integer pageNum, Integer pageSize) {
		//获取第1页，10条内容，默认查询总数count
    	PageHelper.startPage(pageNum, pageSize);
    	//用PageInfo对结果进行包装
    	List<Group> list =  groupMapper.findMyGroups(ShiroUser.getUserId());
    	return new PageInfo<Group>(list);
	}

}
