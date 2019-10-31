package com.web.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	
private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	//method 입력하지 않을 시 default값은 GET
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mnv) {
		logger.debug("---------- AuthController index -----------");		
		mnv.setViewName("auth/login");		
		return mnv;
	}
	
	//method 입력하지 않을 시 default값은 GET
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ModelAndView join(ModelAndView mnv) {
		logger.debug("---------- AuthController index -----------");		
		mnv.setViewName("auth/join");		
		return mnv;
	}
	
}
