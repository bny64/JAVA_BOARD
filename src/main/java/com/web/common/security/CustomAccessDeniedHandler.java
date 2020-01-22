package com.web.common.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * AccessDeniedHandler는 인증을 한 사용자에 한해서 작동됨.
 * */
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String contentType = request.getContentType();
		
		if(contentType != null) {
			
			if(contentType.contains("multipart/form-data") ||
					contentType.contains("application/json") ||
					contentType.contains("application/x-www-form-urlencoded")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/common/error/permissionAjaxError.do");
				dispatcher.forward(request, response);
				
			}
			
		}else {
			
			response.sendRedirect("/common/error/permissionError.do");			
		}
		
	}

}
