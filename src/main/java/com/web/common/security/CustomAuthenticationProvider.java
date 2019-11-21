package com.web.common.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.web.auth.domain.User;
import com.web.auth.service.AuthService;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoding passwordEncoding;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		List<User> user = null;
		
		try {
			user = authService.selectByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user.size() < 1 ) throw new UsernameNotFoundException(email);
		
		CustomUserDetails userDetails = new CustomUserDetails(user.get(0));
		
		if(!passwordEncoding.matches(password, userDetails.getPassword())) throw new BadCredentialsException(email);
		
		if(!userDetails.isEnabled()) new BadCredentialsException(email);
		
		return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
