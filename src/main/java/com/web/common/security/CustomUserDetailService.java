package com.web.common.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.common.security.service.SecurityService;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private SecurityService securityService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, PersistenceException, LockedException {
		//권한정보가 있는 CustomUserDetails
		CustomUserDetails userDetail = null;
		
		List<User> listUser = null;
		//이메일로 사용자 검색
		
		listUser = securityService.selectByEmail(email);
		
		//사용자가 검색되면
		if(listUser.size() > 0) {
			
			List<String> listAuth = new ArrayList<String>();
			List<GrantedAuthority> listGranted = null;
			UserAuthority userAuthority = null;
			User user = listUser.get(0);
			
			if(user.getLoginFailCnt()>=5) throw new LockedException(email);
			
			userDetail = new CustomUserDetails(user);
			
			//권한 정보 조회
			userAuthority = securityService.selectAuth(userDetail.getId());
						
			//권한이 없을 경우
			if(userAuthority.getAuthority().length()<1) {
				
				userDetail.setAuthorities(null);
			//권한이 있을 경우
			}else {
				String[] authorities = userAuthority.getAuthority().split(",");
				
				for(String authority : authorities) {
					listAuth.add(authority);
				}
				
				listGranted = makeGrantedAuthority(listAuth);
				userDetail.setAuthorities(listGranted);
			}
			//계정 잠김 여부
			userDetail.setEnabled("Y".equals(userAuthority.getEnabled()) ? true : false);
			
		}else {
			throw new UsernameNotFoundException(email);
		}
		
		return userDetail;
		
	}

	private static List<GrantedAuthority> makeGrantedAuthority(List<String> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(role)));
		return list;
	}
	
}