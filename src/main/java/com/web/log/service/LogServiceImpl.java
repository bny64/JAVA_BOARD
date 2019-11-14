package com.web.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.log.dao.LogDAO;
import com.web.log.domain.LoginLog;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private LogDAO logDao;

	@Override
	public void writeLoginLog(LoginLog log) throws Exception {
		logDao.writeLoginLog(log);		
	}	
	
}
