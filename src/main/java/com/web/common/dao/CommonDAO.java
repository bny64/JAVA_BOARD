package com.web.common.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CommonDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
}
