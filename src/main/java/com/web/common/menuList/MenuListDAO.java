package com.web.common.menuList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.web.common.dao.CommonDAO;

public class MenuListDAO extends CommonDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getMenuList(String[] url) throws PersistenceException{
		
		Session session = getSession();
		
		List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();
		Map<String, String> menu = new HashMap<String, String>();
		
		String queryStr = "SELECT * FROM menuList WHERE listNo = 1";
		
		Query<Map<String, String>> query = session.createQuery(queryStr);
		menu = query.getSingleResult();
		menuList.add(menu);
		
		for(String element : url) {
			menu.clear();
			queryStr = "SELECT * FROM menuList WHERE url = :url";
			query = session.createQuery(queryStr);
			query.setParameter("url", element);
			menu = query.getSingleResult();
			menuList.add(menu);
		}
		
		return menuList;
	}
	
}
