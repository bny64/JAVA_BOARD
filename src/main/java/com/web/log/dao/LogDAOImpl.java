package com.web.log.dao;

import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.common.dao.CommonDAO;
import com.web.log.domain.LoginLog;

@Repository
@Transactional
public class LogDAOImpl extends CommonDAO implements LogDAO{

	@Override
	public void writeLoginLog(LoginLog log) throws PersistenceException {
		
		sessionFactory.getCurrentSession().save(log);
		
	}

}
