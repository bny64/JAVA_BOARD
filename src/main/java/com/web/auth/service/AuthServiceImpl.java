package com.web.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.auth.dao.AuthDAO;
import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.common.security.CustomUserDetails;

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
	public List<User> selectByEmail(String email) throws Exception {		
		return authDao.selectByEmail(email);
	}

	@Override
	public UserAuthority selectAuth(String id) throws Exception {		
		return authDao.SelectAuth(id);
	}

	@Override
	public void saveAuth(UserAuthority userAuthority) throws Exception {
		authDao.saveAuth(userAuthority);
	}

}
