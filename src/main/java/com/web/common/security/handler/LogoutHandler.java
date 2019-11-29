package com.web.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

//loginSuccess loginFail는 implements로 logout은 extends로 처리
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler{
	
	public LogoutHandler(String defaultLogoutUrl) {
		super.setDefaultTargetUrl(defaultLogoutUrl);
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.onLogoutSuccess(request, response, authentication);
	}
	
}
