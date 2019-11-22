package com.web.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.auth.service.AuthService;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		CustomUserDetails userDetail = null;
		
		try {
		
			List<User> listUser = null;
			
			listUser = authService.selectByEmail(email);
			
			if(listUser.size() > 0) {
				
				List<String> listAuth = new ArrayList<String>();
				List<GrantedAuthority> listGranted = null;
				UserAuthority userAuthority = null;
				
				userDetail = new CustomUserDetails(listUser.get(0));
								
				userAuthority = authService.selectAuth(userDetail.getId());
				String[] authorities = userAuthority.getAuthority().split(",");
				
				for(String authority : authorities) {
					listAuth.add(authority);
				}
				
				listGranted = makeGrantedAuthority(listAuth); 
				userDetail.setAuthorities(listGranted);
				userDetail.setEnabled("Y".equals(userAuthority.getEnabled()) ? true : false);
				
			}else {
				userDetail = new CustomUserDetails();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDetail;
		
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<String> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(role)));
		return list;
	}
	
}
