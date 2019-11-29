package com.web.common.security.handler;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.web.common.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	private MessageUtil msgUtil;
	
	private String email;
	private String errMsg;
	private String defaultFailUrl;	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String userEmail = request.getParameter(email);
		String errormsg = null;
		
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException 
				|| exception instanceof UsernameNotFoundException) {
			errormsg = msgUtil.getMessage("BadCredentials");
		}else if(exception instanceof AuthenticationCredentialsNotFoundException || exception instanceof DisabledException) {
			errormsg = msgUtil.getMessage("NoAuthorities");
		}
		
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("errormsg", errormsg);
		
		request.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		request.getRequestDispatcher(defaultFailUrl).forward(request, response);
	}

}
