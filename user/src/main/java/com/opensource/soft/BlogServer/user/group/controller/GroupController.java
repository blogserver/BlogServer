package com.opensource.soft.BlogServer.user.group.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.user.group.data.Group;
import com.opensource.soft.BlogServer.user.group.service.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;
    
    @ResponseBody
    @RequestMapping(value="/saveGroup", method = RequestMethod.POST)
    public String saveGroup(Group group){
        logger.info(group.toString());
        groupService.save(group);
        return BaseResponse.successJson();
    }

    @ResponseBody
    @RequestMapping(value="/findAllGroup")
    public String findAllGroup(){
        return BaseResponse.successJson(groupService.findAllGroup());
    }

}
