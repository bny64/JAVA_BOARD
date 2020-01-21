package com.web.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.common.excpetion.AccessDeniedException;
import com.web.common.excpetion.UnauthorizedException;

@Controller
@RequestMapping(value = "/common/error")
public class ErrorControllor {
	
	//잘못된 요청을 한 경우
	@RequestMapping(value = "/requestError")
	public ModelAndView requestError(ModelAndView mnv) throws Exception{
		mnv.setViewName("error/requestErrorPage");
		return mnv;
	}

	//인증이 된 후 권한이 없는 요청을 할 경우(페이지 이동)
	@RequestMapping(value = "/permissionError")
	public ModelAndView permissionError(ModelAndView mnv, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mnv.setViewName("error/permissionErrorPage");
		return mnv;
	}
		
	//인증이 된 후 권한이 없는 요청을 할 경우 (Ajax)
	@RequestMapping(value = "/permissionAjaxError")
	public String permissionAjaxError() throws Exception{		
		throw new AccessDeniedException();		
	}
	
	//인증 하기 전 권한이 없는 요청을 한 경우	(Ajax)
	@RequestMapping(value = "/unauthorizedAjaxError")
	public void unauthorizedAjaxError() throws Exception {				
		throw new UnauthorizedException();		
	}
}
