package com.opensource.soft.BlogServer.user.group.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
