package com.web.common.menuList;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuListService {

	@Autowired
	MenuListDAO menuListDao;
	
	public List<Map<String, Object>> getMenuList(List<String> url) throws PersistenceException{
		return menuListDao.getMenuList(url);
	}
	
}
