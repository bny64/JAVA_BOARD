package com.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//method 입력하지 않을 시 default값은 GET
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mnv) {
		logger.debug("---------- MainController index -----------");		
		mnv.setViewName("index_layout");
		return mnv;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView test(ModelAndView mnv, HttpServletRequest request) {
		logger.debug("---------- MainController index -----------");
		logger.debug("---------- Controller index -----------");
		request.getRequestURI();
		request.getRequestURL();
		return mnv;
	}
}
