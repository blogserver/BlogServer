package com.opensource.soft.BlogServer.user.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.opensource.soft.BlogServer.user.user.model.User;

public class ShiroUser {
	
	public static User getUser(){
        Subject subject= SecurityUtils.getSubject();
        if(subject==null)
            return null;
        return (User) subject.getPrincipal();
    }
	
	public static Integer getUserId(){
		return getUser().getId();
	}
}
