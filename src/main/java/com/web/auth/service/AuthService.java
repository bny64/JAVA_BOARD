package com.web.auth.service;

import java.util.List;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.common.security.CustomUserDetails;

public interface AuthService {

	public void join(User user) throws Exception;
	public List<User> selectById(String id) throws Exception;
	public List<User> selectByEmail(String email) throws Exception;
	public UserAuthority selectAuth(String id) throws Exception;
	public void saveAuth(UserAuthority userAuthority) throws Exception;
}
