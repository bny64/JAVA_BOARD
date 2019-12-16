package com.web.log.service;

import javax.persistence.PersistenceException;

import com.web.log.domain.LoginLog;

public interface LogService {

	public void writeLoginLog(LoginLog log) throws PersistenceException;
	
}
