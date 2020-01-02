package com.web.common.jsmap.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.common.jsmap.dao.JsmapDAO;
import com.web.common.jsmap.domain.Jsmap;

@Service
public class JsmapService {

	@Autowired
	JsmapDAO jsmapDao;
	
	public List<Jsmap> getJsmap() throws PersistenceException{
		return jsmapDao.getJsmap();
	};	
	
}
