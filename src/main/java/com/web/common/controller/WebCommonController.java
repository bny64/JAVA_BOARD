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
	
	//���ǿ� ��� Spring security ����(id, name, email, userKey)
	public CustomUserDetails getUserDetails() {
		
		CustomUserDetails userDetails = null;
		
		//spring security�� principal�� CustomUserDetails�� ����
		if(SecurityContextHolder.getContext().getAuthentication()!=null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userDetails = mapper.convertValue(principal, CustomUserDetails.class);
		}		
		
		return userDetails;
	}
	
	//���� ���� ������� ����
	public User getSessionUser() {
		
		CustomUserDetails userDetails = getUserDetails();
		
		User user = authService.selectByUserKey(userDetails.getUserKey());
		
		return user;		
	}
	
}
