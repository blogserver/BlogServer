package com.opensource.soft.BlogServer.user.user.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.soft.BlogServer.common.BaseResponse;
import com.opensource.soft.BlogServer.user.common.shiro.ShiroUser;
import com.opensource.soft.BlogServer.user.common.util.ValidateCode;
import com.opensource.soft.BlogServer.user.user.model.User;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");
		model.addAttribute("user",ShiroUser.getUser());
		return "user/home";
	}

	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String userInfo(){
		User user = ShiroUser.getUser();
		Map<String,String> userinfo = new HashMap<String, String>();
		userinfo.put("name",user.getNickname());
		userinfo.put("photo",user.getPhoto());
		return BaseResponse.successJson(userinfo);
	}

	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public String unauth() {
		return "user/unauth";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String patternUrl() {
		Subject user = SecurityUtils.getSubject();
		if(user.isAuthenticated()){
			return "redirect:/user/";
		}else{
			return "redirect:/user/login";
		}
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logindata(HttpServletResponse response, HttpServletRequest request,String loginname ,String password ,String validateCode , Model model) {
		String sessionValidateCode = (String) request.getSession().getAttribute("validateCode");
		if(!validateCode.equals(sessionValidateCode)) {
			model.addAttribute("errMsg", "验证码错误");
			return "user/login";
		}
		
		Subject user = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(loginname,password);
        try
        {
            // 会调用 shiroDbRealm 的认证方法
            user.login(token);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return "redirect:/user/login";
        }
        
        Cookie cookie = new Cookie("username", ShiroUser.getUser().getNickname());
        cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/user/";
	}
	
	/** 
    * user logout
    */  
    @RequestMapping(value="/logout",method=RequestMethod.GET)  
    public String logout(){  
         SecurityUtils.getSubject().logout();  
         return "redirect:/user/login";  
    }  

    
    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode",method=RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        //TODO 本地可以，服务器上不行 ，https图片显示不出来 目前先写死  1234
        verifyCode = "1234";
        logger.info("图片验证码：{}",verifyCode);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        
        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
        ImageIO.write(bim, "JPEG", tmp);
        tmp.close();
        Integer contentLength = tmp.size();
        response.setHeader("content-length", contentLength + "");
        response.getOutputStream().write(tmp.toByteArray());// 将内存中的图片通过流动形式输出到客户端
    }
}
