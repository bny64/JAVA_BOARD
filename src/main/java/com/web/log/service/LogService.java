package com.web.log.service;

import com.web.log.domain.LoginLog;

public interface LogService {

	public void writeLoginLog(LoginLog log) throws Exception;
	
}
