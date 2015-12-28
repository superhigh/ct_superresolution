package com.lm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lm.entity.UserEntity;
import com.lm.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping("/calendar")
	public String calendar( ModelMap modelMap ,HttpServletRequest request) {
		return "calendar";
	}
	@RequestMapping("/faq")
	public String faq( ModelMap modelMap ,HttpServletRequest request) {
		return "faq";
	}
	
	@RequestMapping("/login")
	public String login(ModelMap modelMap ,HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping("/index")
	public String index( ModelMap modelMap ,HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/messages")
	public String messages( ModelMap modelMap ,HttpServletRequest request) {
		return "messages";
	}
	
	@RequestMapping("/paragraphs")
	public String paragraphs( ModelMap modelMap ,HttpServletRequest request) {
		return "paragraphs";
	}
	
	@RequestMapping("/settings")
	public String settings( ModelMap modelMap ,HttpServletRequest request) {
		return "settings";
	}
	
	@RequestMapping("/stream")
	public String stream( ModelMap modelMap ,HttpServletRequest request) {
		return "stream";
	}
	
	@RequestMapping("/users")
	public String users( ModelMap modelMap ,HttpServletRequest request) {
		return "users";
	}
	
	@RequestMapping (value="/doLogin",method=RequestMethod.POST)
	public String  doLogin(String email, String password,ModelMap modelMap ,HttpServletRequest request){
		UserEntity user=userService.getUserEntityByEmail(email);
		if(user!=null && user.getPassword().equals(password)){
			request.getSession().setAttribute("ISLOGIN", "TRUE");
			request.getSession().setAttribute("USERNAME", user.getUsername());
			return "index";
		}
		else 
			return "login";
	}
	@RequestMapping (value="/loginOut",method=RequestMethod.GET)
	public String  loginOut(ModelMap modelMap ,HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
	
	


}