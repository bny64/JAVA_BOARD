package com.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.web.common.menuList.MenuList;

public class TestClass {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/application/database-context.xml");
		SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
		Session session = sf.openSession();
		session.beginTransaction();

		try {
			menuListQuery(session);
			Field[] fields = MenuList.class.getDeclaredFields();
			List<String> a = new ArrayList<String>();
			for(Field b : fields) {
				a.add(b.getName());
			}
			
			System.out.println(a.toArray(new String[0]));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public static void menuListQuery(Session session) throws Exception {

		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		Map<String, Object> nativeQueryResult = new HashMap<String, Object>();		
		String[] columns = {"listNo","name","type","depth","parentListNo","url"};
		String[] urls = {"/board", "/registBoard"};		
		
		String queryStr = "SELECT * FROM menuList WHERE listNo = 1";
		
		Query query = session.createNativeQuery(queryStr);
		Object[] result = (Object[]) query.getSingleResult();
		
		nativeQueryResult = parser(columns, result);
		menuList.add(nativeQueryResult);
		
		for(String url : urls) {
			queryStr = "SELECT * FROM menuList WHERE url = :url";
			query = session.createNativeQuery(queryStr);
			query.setParameter("url", url);
			result = (Object[]) query.getSingleResult();
			nativeQueryResult = parser(columns, result);
			menuList.add(nativeQueryResult);
		}
		
		System.out.println(menuList);
		
	}	
	
	public static Map<String, Object> parser(String[] columns,Object[] results){
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		for(int i = 0; i<columns.length; i++) {
			returnMap.put(columns[i], results[i]);
		}
		
		return returnMap;
		
	}
}
