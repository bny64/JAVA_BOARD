package com.web.common.dao;


import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CommonDAO{

	@Autowired
	public SessionFactory sessionFactory;
	
	public CriteriaBuilder getCriteria() throws Exception{		
		return sessionFactory.getCurrentSession().getCriteriaBuilder();
	}
	
}
