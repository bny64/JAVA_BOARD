package com.web.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.auth.dao.AuthDAO;
import com.web.auth.domain.User;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDAO authDao;

	@Override
	public void join(User user) throws Exception {
		authDao.join(user);
	}

	@Override
	public List<User> selectById(String id) throws Exception {		
		return authDao.selectById(id);
		
	}

	@Override
	public User selectByEmail(String email) throws Exception {
		authDao.selectByEmail(email);
		return authDao.selectByEmail(email);
	}
	
}
