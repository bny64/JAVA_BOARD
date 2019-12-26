package com.web.auth.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;

public interface AuthDAO {
	
	public void join(User user) throws PersistenceException;
	public List<User> selectById(String id) throws PersistenceException;
	public List<User> selectByEmail(String email) throws PersistenceException;
	public UserAuthority SelectAuth(String id) throws PersistenceException;
	public User selectByUserKey(String userKey) throws PersistenceException;
	public void saveAuth(UserAuthority userAuthority) throws PersistenceException;
}
