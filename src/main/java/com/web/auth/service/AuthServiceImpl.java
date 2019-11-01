package com.web.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
