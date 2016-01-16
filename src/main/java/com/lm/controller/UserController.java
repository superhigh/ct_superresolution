package com.lm.controller;

import com.lm.entity.ImageEntity;
import com.lm.entity.UserEntity;
import com.lm.service.ImageService;
import com.lm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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

	@Autowired
	private ImageService imageService;
	public void setImageService(ImageService ImageSerivce){this.imageService=imageService;}
	public ImageService getImageService(){return this.imageService;}
	
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

	@RequestMapping("/register")
	public  String register(ModelMap modelMap,HttpServletRequest request){
		return "register";
	}
	
	@RequestMapping("/index")
	public String index( ModelMap modelMap ,HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/messages")
	public String messages( ModelMap modelMap ,HttpServletRequest request) {
		return "messages";
	}

	@RequestMapping("/thumbnailImage")
	public String thumbnailImage(ModelMap modelMap,HttpServletRequest request){
		List<ImageEntity> list=imageService.getImageEntities();
		modelMap.addAttribute("imageEntities",list);
		return "thumbnailImage";
	}
	@RequestMapping("/delete")
	public String deleteImage(ModelMap modelMap,HttpServletRequest request){
		imageService.deleteImageEntitiesById(request.getParameter("imageId"));
		List<ImageEntity> list=imageService.getImageEntities();
		modelMap.addAttribute("imageEntities",list);
		return "thumbnailImage";
	}
	@RequestMapping(method=RequestMethod.GET,value="/paragraphs")
	public String paragraphs( ModelMap modelMap ,HttpServletRequest request) {
		modelMap.addAttribute("path",request.getParameter("imageId"));
		return "paragraphs";
	}
	//处理截取图片的放大
	@RequestMapping(value="/paragraphs",method=RequestMethod.POST)
	public String paragraphsImgCrods(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		session.setAttribute("x",request.getParameter("x1"));
		session.setAttribute("y",request.getParameter("y1"));
		session.setAttribute("w",request.getParameter("w"));
		session.setAttribute("h",request.getParameter("h"));
		session.setAttribute("path",request.getParameter("imgPath"));
		return "paragraphsImgCrods";
	}
	@RequestMapping("/imshow")
	public void imshow(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		String imgPath=session.getServletContext().getRealPath("/")+"resources/"+session.getAttribute("path");
		response.setContentType("image/jpeg;charset=utf8");
		OutputStream os=response.getOutputStream();
		Integer x=Integer.parseInt((String) session.getAttribute("x"));
		Integer y=Integer.parseInt((String)session.getAttribute("y"));
		Integer w=Integer.parseInt((String)session.getAttribute("w"));
		Integer h=Integer.parseInt((String)session.getAttribute("h"));
		ImgCut.cutImg(os,imgPath,x,y,w,h);
		os.close();
		response.flushBuffer();

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
	@RequestMapping("/upload")
	public String upload(ModelMap modelMap,HttpServletRequest request){

		return "";
	}


}