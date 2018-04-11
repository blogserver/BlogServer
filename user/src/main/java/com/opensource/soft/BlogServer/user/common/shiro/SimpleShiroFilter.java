package com.opensource.soft.BlogServer.user.common.shiro;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleShiroFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/**
		 * 不能为 * 不能有两个值    否则报错
		 * 
		 * Failed to load https://www.chinaopensource.top/blog/likeBlog:
		 * Redirect from 'https://www.chinaopensource.top/blog/likeBlog' to
		 * 'https://www.chinaopensource.top/user/login' has been blocked by CORS
		 * policy: The value of the 'Access-Control-Allow-Origin' header in the
		 * response must not be the wildcard '*' when the request's credentials
		 * mode is 'include'. Origin 'http://www.chinaopensource.top' is
		 * therefore not allowed access. The credentials mode of requests
		 * initiated by the XMLHttpRequest is controlled by the withCredentials
		 * attribute.
		 */
		// response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Origin", "http://www.chinaopensource.top");
//		response.addHeader("Access-Control-Allow-Origin", "http://localhost");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE,OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Max-Age", "1800");// 30 min
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		filterChain.doFilter(request, response);
	}

}