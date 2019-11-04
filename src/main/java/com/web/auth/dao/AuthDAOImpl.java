package com.web.auth.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.auth.domain.User;

@Repository
@Transactional
public class AuthDAOImpl implements AuthDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void join(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);		
	}	
}
