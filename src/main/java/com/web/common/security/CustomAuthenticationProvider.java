package com.web.common.security;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.web.common.security.service.SecurityService;
import com.web.log.domain.LoginLog;
import com.web.log.service.LogService;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private PasswordEncoding passwordEncoding;
	
	@Autowired
	private LogService logService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException, PersistenceException {
		
		/**
		 * Security에서 에러 났을 때 처리가 안되어 있음.
		 * */
		/*
		BadCredentialException	 비밀번호가 일치하지 않을 때 던지는 예외	
		InternalAuthenticationServiceException	 존재하지 않는 아이디일 때 던지는 예외
		AuthenticationCredentialNotFoundException	 인증 요구가 거부됐을 때 던지는 예외
		LockedException	 인증 거부 - 잠긴 계정
		DisabledException	 인증 거부 - 계정 비활성화
		AccountExpiredException	 인증 거부 - 계정 유효기간 만료
		CredentialExpiredException	 인증 거부 - 비밀번호 유효기간 만료 
		*/
		
		Map<String, String> principal = new HashMap<String, String>();
		LoginLog log = new LoginLog();
		
		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		CustomUserDetails userDetails = null;
				
		userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(email);
				
		if(!passwordEncoding.matches(password, userDetails.getPassword())) {
			Map<String, Object> reqMap = new HashMap<String, Object>();			
			int loginCnt = securityService.getLoginFailCnt(email);
			loginCnt++;
			reqMap.put("email", email);
			reqMap.put("loginFailCnt", loginCnt);
			securityService.addLoginFailCnt(reqMap);
			throw new BadCredentialsException(email);
		}
		
		if(userDetails.getAuthorities()==null) 
			throw new DisabledException(email);
		
		securityService.resetLoginFailCnt(email);
		
		principal.put("name", userDetails.getName());
		principal.put("id", userDetails.getId());
		principal.put("email", userDetails.getEmail());
		principal.put("userKey", userDetails.getUserKey());
		
		log.setJoinType(userDetails.getJoinType());
		log.setName(userDetails.getName());
		log.setId(userDetails.getId());
		
		logService.writeLoginLog(log);
		
		return new UsernamePasswordAuthenticationToken(principal, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
