package com.web.common.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.auth.domain.User;

public class CustomUserDetails extends User implements UserDetails{

	public CustomUserDetails() {}
	
	public CustomUserDetails(User user) {
		super(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getJoinType(), user.getUserType());
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1500271736617212227L;
	
	private String authority;
	private boolean enabled;	
	
	//������ �����ִ� ���� ����� �����Ѵ�.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
	        auth.add(new SimpleGrantedAuthority(authority));
	        return auth;
	}

	//������ ��й�ȣ�� �����Ѵ�.
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	//������ �̸��� �����Ѵ�.
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	//������ ������� �ʾҴ� �� �����Ѵ�. (true: ����ȵ�)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//������ ������� �ʾҴ� �� �����Ѵ�. (true: ����� ����)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//��й�ȣ�� ������� �ʾҴ� �� �����Ѵ�. (true: ����ȵ�)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//������ Ȱ��ȭ(��밡��)�� �� �����Ѵ�. (true: Ȱ��ȭ)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}
