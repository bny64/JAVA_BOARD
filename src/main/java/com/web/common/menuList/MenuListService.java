package com.web.common.menuList;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuListService {

	@Autowired
	MenuListDAO menuListDao;
	
	public List<Map<String, Object>> getMenuList(List<String> url) throws PersistenceException{
		return menuListDao.getMenuList(url);	
	}
	
	public List<Map<String, Object>> getMenuListByDepth(String depth) throws PersistenceException {
		return menuListDao.getMenuListByDepth(depth);
	}
	
	public List<Map<String, Object>> getMenuListAll() throws PersistenceException {
		return menuListDao.getMenuListAll();
	}
	
}
