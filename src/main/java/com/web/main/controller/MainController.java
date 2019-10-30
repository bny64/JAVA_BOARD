package com.web.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	private HibernateTemplate hibernateTemplate;
	
	//method 입력하지 않을 시 default값은 GET
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mnv) {
		logger.debug("---------- MainController index -----------");		
		mnv.setViewName("index");		
		return mnv;
	}
}
