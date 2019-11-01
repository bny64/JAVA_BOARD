package com.web.auth.dao;

import com.web.auth.domain.User;

public interface AuthDAO {
	
	public void join(User user) throws Exception;
	
}
