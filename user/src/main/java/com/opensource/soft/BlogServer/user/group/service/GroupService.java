package com.opensource.soft.BlogServer.user.group.service;

import java.util.List;

import com.opensource.soft.BlogServer.user.group.data.Group;

public interface GroupService {
	
    int save(Group group);

	List<Group> findAllGroup();
}
