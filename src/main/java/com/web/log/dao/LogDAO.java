package com.web.log.dao;

import javax.persistence.PersistenceException;

import com.web.log.domain.LoginLog;

public interface LogDAO {
	public void writeLoginLog(LoginLog log) throws PersistenceException;
}
