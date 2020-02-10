package com.web.common.menuList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.common.dao.CommonDAO;
import com.web.common.util.ParserUtil;

@Repository
@Transactional
public class MenuListDAO extends CommonDAO{
	
	@SuppressWarnings("rawtypes")
	@Autowired
	ParserUtil parserUtil;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMenuList(List<String> urls) throws PersistenceException{

		Session session = getSession();
		
		String[] columns = parserUtil.getClassFieldNames(MenuList.class);
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		Map<String, Object> nativeQueryResult = new HashMap<String, Object>();
		
		String queryStr = "SELECT * FROM menuList WHERE listNo = 1";
		
		Query<?> query = session.createNativeQuery(queryStr);
		Object[] result = (Object[]) query.getSingleResult();
		
		nativeQueryResult = parserUtil.nativeQueryParser(columns, result);
		menuList.add(nativeQueryResult);
		
		for(String url : urls) {
			
			queryStr = "SELECT * FROM menuList WHERE url = :url";
			query = session.createNativeQuery(queryStr);
			query.setParameter("url", url);
			int size = query.list().size();
			if(size > 0) {
				result = (Object[]) query.list().get(0);
				nativeQueryResult = parserUtil.nativeQueryParser(columns, result);
				menuList.add(nativeQueryResult); 
			}else {
				break;
			}
		}
		
		return menuList;
	}
	
}
