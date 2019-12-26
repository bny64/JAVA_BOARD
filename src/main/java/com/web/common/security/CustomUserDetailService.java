package com.web.common.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, PersistenceException {
		//���������� �ִ� CustomUserDetails
		CustomUserDetails userDetail = null;
		
		List<User> listUser = null;
		//�̸��Ϸ� ����� �˻�
		
		listUser = securityService.selectByEmail(email);
		
		//����ڰ� �˻��Ǹ�
		if(listUser.size() > 0) {
			
			List<String> listAuth = new ArrayList<String>();
			List<GrantedAuthority> listGranted = null;
			UserAuthority userAuthority = null;
			User user = listUser.get(0);
			
			userDetail = new CustomUserDetails(user);
			
			//���� ���� ��ȸ
			userAuthority = securityService.selectAuth(userDetail.getId());
						
			//������ ���� ���
			if(userAuthority.getAuthority().length()<1) {
				
				userDetail.setAuthorities(null);
			//������ ���� ���
			}else {
				String[] authorities = userAuthority.getAuthority().split(",");
				
				for(String authority : authorities) {
					listAuth.add(authority);
				}
				
				listGranted = makeGrantedAuthority(listAuth);
				userDetail.setAuthorities(listGranted);
			}
			//���� ��� ����
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
