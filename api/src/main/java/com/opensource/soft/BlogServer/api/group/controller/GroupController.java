package com.opensource.soft.BlogServer.api.group.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.api.group.service.GroupService;
import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping(value="/group")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value="/findAllGroup")
    public String findAllGroup(){
    	logger.info("查询所有分类");
        return BaseResponse.successJson(groupService.findAllGroup());
    }
    
    @RequestMapping(value="/findGroupById")
    public String findGroupById(Integer groupId){
    	logger.info("查询{}分类",groupId);
        return BaseResponse.successJson(groupService.findGroupById(groupId));
    }
    

}
