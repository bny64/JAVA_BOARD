package com.web.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.common.controller.WebCommonController;


@Controller
public class MainController extends WebCommonController{
	
	private Logger logger = LoggerFactory.getLogger(MainController.class);
			
	
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mnv) {		
		
		logger.debug("---------- MainController index -----------");		
		mnv.setViewName("index");
		
		return mnv;
	}
}
