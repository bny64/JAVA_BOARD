package com.web.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	private String defaultSuccessUrl;
	private String email;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//spring security에서 message를 세션에 담기 때문에 삭제
		request.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		 redirectStratgy.sendRedirect(request, response, defaultSuccessUrl);
		 
	}

}
