package com.web.auth.dao;

import java.util.List;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;

public interface AuthDAO {
	
	public void join(User user) throws Exception;	
	public List<User> selectById(String id) throws Exception;
	public List<User> selectByEmail(String email) throws Exception;
	public UserAuthority SelectAuth(String id) throws Exception;
}
