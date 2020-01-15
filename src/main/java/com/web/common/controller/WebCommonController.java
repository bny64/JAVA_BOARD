package com.web.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.auth.domain.User;
import com.web.auth.service.AuthService;
import com.web.common.security.CustomUserDetails;

public class WebCommonController{
	
	@Autowired
	private AuthService authService;
	
	private ObjectMapper mapper;
	
	public WebCommonController() {
		this.mapper = new ObjectMapper();
	}
	
	//세션에 담긴 Spring security 정보(id, name, email, userKey)
	public CustomUserDetails getUserDetails() {
		
		CustomUserDetails userDetails = null;
		
		//spring security의 principal을 CustomUserDetails로 변경
		if(SecurityContextHolder.getContext().getAuthentication()!=null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userDetails = mapper.convertValue(principal, CustomUserDetails.class);
		}		
		
		return userDetails;
	}
	
	//현재 세션 사용자의 정보
	public User getSessionUser() {
		
		CustomUserDetails userDetails = getUserDetails();
		
		User user = authService.selectByUserKey(userDetails.getUserKey());
		
		return user;		
	}
	
}