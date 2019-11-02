package com.web.auth.controller;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.auth.domain.User;
import com.web.auth.service.AuthService;
import com.web.common.controller.WebCommonController;

@Controller
@RequestMapping(value = "/auth")
public class AuthController extends WebCommonController{
	
private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthService authService;

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
		return mnv;
	}
	
	//가입 폼 전송
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public ModelAndView joinForm(ModelAndView mnv, @RequestParam Map<String, Object> req) throws Exception{
		logger.debug("---------- [AuthController]:[joinForm] -----------");
		
		User user = new User();
		
		user.setUserKey(UUID.randomUUID().toString());
		user.setId(req.get("id").toString());
		user.setPassword(req.get("password").toString());
		user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(req.get("birth").toString()));
		user.setEmail(req.get("email").toString());
		user.setJoinType("JAVA");
		user.setName(req.get("name").toString());
		user.setEmailYn("N");
		authService.join(user);
		
		mnv.setViewName("auth/login");
		return mnv;
	}
	
}
