package com.web.auth.service;

import java.util.List;

import com.web.auth.domain.User;

public interface AuthService {

	public void join(User user) throws Exception;
	public List<User> selectById(String id) throws Exception;
	public List<User> selectByEmail(String email) throws Exception;
}
