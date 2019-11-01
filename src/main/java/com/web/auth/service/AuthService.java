package com.web.auth.service;

import com.web.auth.domain.User;

public interface AuthService {

	public void join(User user) throws Exception;
	
}
