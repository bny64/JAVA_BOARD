package com.web.auth.dao;

import java.util.List;

import com.web.auth.domain.User;

public interface AuthDAO {
	
	public void join(User user) throws Exception;	
	public List<User> selectById(String id) throws Exception;
	public User selectByEmail(String email) throws Exception;
}
