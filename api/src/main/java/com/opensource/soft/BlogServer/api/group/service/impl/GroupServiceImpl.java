package com.opensource.soft.BlogServer.api.group.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensource.soft.BlogServer.api.group.data.Group;
import com.opensource.soft.BlogServer.api.group.mapper.GroupMapper;
import com.opensource.soft.BlogServer.api.group.service.GroupService;

@Service(value="groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;

	@Override
	public List<Group> findAllGroup() {
		return groupMapper.selectAllGroup();
	}

	@Override
	public Group findGroupById(Integer groupId) {
		return groupMapper.selectById(groupId);
	}

}
