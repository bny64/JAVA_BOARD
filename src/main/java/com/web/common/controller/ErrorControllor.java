package com.web.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/common/error")
public class ErrorControllor {

	@RequestMapping(value = "/permissionError")
	public ModelAndView permissionError(ModelAndView mnv) throws Exception{
		mnv.setViewName("error/permissionErrorPage");
		return mnv;
	}
	
	@RequestMapping(value = "/requestError")
	public ModelAndView requestError(ModelAndView mnv) throws Exception{
		mnv.setViewName("error/requestErrorPage");
		return mnv;
	}
	
}
