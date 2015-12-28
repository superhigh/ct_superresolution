package com.lm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception err)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mav) throws Exception {
		String username=(String) request.getSession().getAttribute("USERNAME");
		if(username!=null){
			mav.addObject("username", username);
		}else {
			mav.addObject("username", "游客");
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		String uri=request.getRequestURI();
		System.out.println(uri);
		if(uri.equals("/ct/login")||uri.equals("/ct/regist")||uri.equals("/ct/doLogin")){
			return true;
		}
		String str = (String) request.getSession().getAttribute("ISLOGIN");
		System.out.println(str);
		if(str!=null && str.equals("TRUE")){
			
			return true;
		}
		response.sendRedirect("/ct/login");
		return false;
	}
}