package com.web.common.security.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.common.security.dao.SecurityDAO;

@Service
public class SecurityService{

	@Autowired
	private SecurityDAO securityDao;
	
	public List<User> selectByEmail(String email) throws PersistenceException {		
		return securityDao.selectByEmail(email);
	}
	
	public UserAuthority selectAuth(String id) throws PersistenceException {		
		return securityDao.SelectAuth(id);
	}
	
}
