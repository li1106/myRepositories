package com.ldd.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterCeptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// 来自于跳转页面的url地址
		String referer = request.getHeader("Referer");
		// 存储到session的会话当中
		request.getSession().setAttribute("refererUrl", referer);
		System.out.println("login@@@@@from@@@@@"+referer);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
