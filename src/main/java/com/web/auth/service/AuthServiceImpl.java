package com.web.auth.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.auth.dao.AuthDAO;
import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDAO authDao;

	@Override
	public void join(User user) throws PersistenceException {
		authDao.join(user);
	}

	@Override
	public List<User> selectById(String id) throws PersistenceException {		
		return authDao.selectById(id);
		
	}

	@Override
	public List<User> selectByEmail(String email) throws PersistenceException {		
		return authDao.selectByEmail(email);
	}

	@Override
	public UserAuthority selectAuth(String id) throws PersistenceException {		
		return authDao.SelectAuth(id);
	}

	@Override
	public void saveAuth(UserAuthority userAuthority) throws PersistenceException {
		authDao.saveAuth(userAuthority);
	}

	@Override
	public User selectByUserKey(String userKey) throws PersistenceException {
		return authDao.selectByUserKey(userKey);
	}

	@Override
	public List<User> selectEmailByIdName(Map<String, Object> req) throws PersistenceException {		
		return authDao.selectEmailByIdName(req);
	}

}
