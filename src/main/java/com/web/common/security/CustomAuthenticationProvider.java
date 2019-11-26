package com.web.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private PasswordEncoding passwordEncoding;
		
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		/*
		BadCredentialException	 비밀번호가 일치하지 않을 때 던지는 예외	
		InternalAuthenticationServiceException	 존재하지 않는 아이디일 때 던지는 예외
		AuthenticationCredentialNotFoundException	 인증 요구가 거부됐을 때 던지는 예외
		LockedException	 인증 거부 - 잠긴 계정
		DisabledException	 인증 거부 - 계정 비활성화
		AccountExpiredException	 인증 거부 - 계정 유효기간 만료
		CredentialExpiredException	 인증 거부 - 비밀번호 유효기간 만료 
		*/
		
		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserDetails userDetails = userDetailService.loadUserByUsername(email);
		
		if(!passwordEncoding.matches(password, userDetails.getPassword())) 
			throw new BadCredentialsException(email);
		
		if(userDetails.getAuthorities()==null) 
			throw new DisabledException(email);
		
		return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
