package com.web.auth.controller;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.auth.domain.User;
import com.web.auth.service.AuthService;
import com.web.common.controller.WebCommonController;
import com.web.common.resolver.CommandMap;
import com.web.common.security.Security;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends WebCommonController{
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AuthService authService;

	@Autowired
	private Security security;
	
	//method 입력하지 않을 시 default값은 GET
	//로그인 화면 이동
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mnv) throws Exception {
		logger.debug("---------- [AuthController]:[login] -----------");		
		mnv.setViewName("auth/login");		
		return mnv;
	}
	
	//가입 화면 이동
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ModelAndView join(ModelAndView mnv) throws Exception {
		logger.debug("---------- [AuthController]:[join] -----------");		
		mnv.setViewName("auth/join");
		System.out.println(authService.selectById("bny64"));
		return mnv;
	}
	
	//가입 폼 전송
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public ModelAndView joinForm(ModelAndView mnv, CommandMap map) throws Exception{
		logger.debug("---------- [AuthController]:[joinForm] -----------");
		
		User user = new User();
		
		user.setUserKey(UUID.randomUUID().toString());
		user.setId(map.get("id").toString());	
		user.setPassword(security.hashSHA256(map.get("password").toString()));
		user.setEmail(map.get("email").toString());
		user.setName(map.get("name").toString());
		user.setJoinType("java");
		if(map.get("phoneNumber")!= null) user.setPhoneNumber(map.get("phoneNumber").toString());
		if(map.get("imgPath")!= null) user.setImgPath(map.get("imgPath").toString());
		if(map.get("introduce")!=null) user.setIntroduce(map.get("introduce").toString());
		if(map.get("birth")!= null) user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("birth").toString()));
		user.setEmailYn(map.get("emailYn").toString());
		user.setUserType("D");
		
		authService.join(user);
		
		mnv.setViewName("auth/login");
		return mnv;
	}
	
}
