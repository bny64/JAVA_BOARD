package com.web.common.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String contentType = request.getContentType();
		
		if(contentType != null) {
		
			if(contentType.contains("multipart/form-data") ||
					contentType.contains("application/json") ||
					contentType.contains("application/x-www-form-urlencoded")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/error/unauthorizedAjaxError.do");
				dispatcher.forward(request, response);
				
			}			
			
		}else {
			response.sendRedirect("/auth/login.do");
		}
		
		
		
	}

}
