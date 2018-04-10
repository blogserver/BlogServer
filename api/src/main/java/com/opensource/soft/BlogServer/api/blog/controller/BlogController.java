package com.opensource.soft.BlogServer.api.blog.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opensource.soft.BlogServer.api.blog.model.Visit;
import com.opensource.soft.BlogServer.api.blog.service.BlogService;
import com.opensource.soft.BlogServer.common.BaseResponse;

@RestController
@RequestMapping("/blog")
public class BlogController{
	
	@Autowired
	private BlogService blogService;
	
	/**
	 * 获取最新的blog内容
	 * @return
	 */
	@RequestMapping(value = "/news" , method = RequestMethod.GET)
	public String findNewBlogs() {
		return BaseResponse.successJson(blogService.findNewBlogs());
	}
	
	/**
	 * blog访问 记录
	 * @return
	 */
	@RequestMapping(value = "/visit" , method = RequestMethod.POST)
	public String visit(HttpServletRequest request,Visit visit) {
		String ip = getRemoteAddress(request);
		String mac = getMACAddress(ip);
		System.out.println("ip------------->"+ip+",mac---------------->"+mac);
		visit.setIp(ip);
		visit.setMac(mac);
		blogService.visitHistory(visit);
		return BaseResponse.successJson();
	}

	public String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
	public String getMACAddress(String ip) {
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -a " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //if (str.indexOf("MAC Address") > 1) {
                    if (str.indexOf("MAC") > 1) {
                        macAddress = str.substring(
                                str.indexOf("=") + 2, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }
	
}
