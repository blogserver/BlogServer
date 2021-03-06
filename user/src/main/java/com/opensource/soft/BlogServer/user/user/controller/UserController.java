package com.opensource.soft.BlogServer.user.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.common.ErrorMessage;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;
import com.opensource.soft.BlogServer.user.user.model.Follow;
import com.opensource.soft.BlogServer.user.user.model.User;
import com.opensource.soft.BlogServer.user.user.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录用户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Model model) {
		logger.info("Welcome home!");
		model.addAttribute("user",ShiroUser.getUser());
		return "user/info";
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, User user, String validateCode){
		String sessionValidateCode = (String) request.getSession().getAttribute("validateCode");
		if(!validateCode.equals(sessionValidateCode)) {
			return BaseResponse.errorJson(ErrorMessage.ERR_SYSTERM);
		}
		this.userService.insert(user);
		return BaseResponse.successJson();
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequestMapping(value="/updateUserPage", method = RequestMethod.GET)
	public String updateUserPage(Integer id, Model model){
		User user = this.userService.selectByUserId(id);
		model.addAttribute("user",user);
		return "common/user/updateUser";
	}
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	public String updateUser(User user){
		this.userService.updateByUserId(user);
		return BaseResponse.successJson();
	}
	
    @ResponseBody
    @RequestMapping(value="/followUser", method = RequestMethod.POST)
    public String followUser(Follow follow){
    	logger.info(follow.toString());
    	userService.followUser(follow);
        return BaseResponse.successJson();
    }
	
}
