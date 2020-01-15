package com.web.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.auth.domain.User;

public class CustomUserDetails extends User implements UserDetails{

	public CustomUserDetails() {}
	
	public CustomUserDetails(User user) {
		super(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getJoinType(), user.getUserType(), user.getUserKey());
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1500271736617212227L;
	
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;	
	
	//계정이 갖고있는 권한 목록을 리턴한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		    
	     return authorities;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {		
		this.authorities = authorities;
	}
	
	//계정의 비밀번호를 리턴한다.
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	//계정의 이름을 리턴한다.
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	//계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}