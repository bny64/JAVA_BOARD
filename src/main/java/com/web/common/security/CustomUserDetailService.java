package com.web.common.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.web.auth.domain.User;
import com.web.auth.service.AuthService;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		List<User> user = null;
		try {
			user = authService.selectByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(user.size() < 1)	throw new UsernameNotFoundException(email);
		
		return new CustomUserDetails(user.get(0));
	}

}
