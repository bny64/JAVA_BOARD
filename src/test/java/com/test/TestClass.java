package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.web.common.util.ParserUtil;

public class TestClass {

	private static Logger logger = LoggerFactory.getLogger(TestClass.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/application/database-context.xml");
		SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
		Session session = sf.openSession();
		session.beginTransaction();

		try {			
			maxValue(session);

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void menuListQuery(Session session) throws Exception {

		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		Map<String, Object> nativeQueryResult = new HashMap<String, Object>();
		String[] columns = { "listNo", "name", "type", "depth", "parentListNo", "url" };
		String[] urls = { "/board", "/registBoard" };

		String queryStr = "SELECT * FROM menuList WHERE listNo = 1";

		Query query = session.createNativeQuery(queryStr);
		Object[] result = (Object[]) query.getSingleResult();

		nativeQueryResult = parser(columns, result);
		menuList.add(nativeQueryResult);

		for (String url : urls) {
			queryStr = "SELECT * FROM menuList WHERE url = :url";
			query = session.createNativeQuery(queryStr);
			query.setParameter("url", url);
			result = (Object[]) query.getSingleResult();
			nativeQueryResult = parser(columns, result);
			menuList.add(nativeQueryResult);
		}

		System.out.println(menuList);

	}

	public static Map<String, Object> parser(String[] columns, Object[] results) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		for (int i = 0; i < columns.length; i++) {
			returnMap.put(columns[i], results[i]);
		}

		return returnMap;

	}

	@SuppressWarnings("unchecked")
	public static void maxValue(Session session) {

		@SuppressWarnings("rawtypes")
		ParserUtil<?> parserUtil = new ParserUtil();

		JSONArray sortArray = new JSONArray();
		JSONObject resultJSON = new JSONObject();
		
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		List<Object[]> resultList = new ArrayList<Object[]>();
		Map<String, Object> nativeQueryResult = new HashMap<String, Object>();
		String[] columns = { "listNo", "name", "type", "depth", "parentListNo", "url", "viewYn" };

		String queryStr = "SELECT * FROM menuList";
		Query<?> query = session.createNativeQuery(queryStr);
		resultList = (List<Object[]>) query.list();

		for (Object[] menu : resultList) {
			nativeQueryResult = parserUtil.nativeQueryParser(columns, menu);
			menuList.add(nativeQueryResult);
		}

		int maxDepth = (int) Collections.max(menuList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {

				int o1Dep = (int) o1.get("depth");
				int o2Dep = (int) o2.get("depth");
				int result = 0;

				if (o1Dep < o2Dep)
					result = -1;
				else if (o1Dep > o2Dep)
					result = 1;
				return result;
			}

		}).get("depth");

		Collections.sort(menuList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {

				int o1Dep = (int) o1.get("depth");
				int o2Dep = (int) o2.get("depth");
				int result = 0;

				if (o1Dep < o2Dep)
					result = -1;
				else if (o1Dep > o2Dep)
					result = 1;
				return result;

			}
		});

		for (int i = 0; i < menuList.size(); i++) {

			int depth = (int) menuList.get(i).get("depth");

			if (sortArray.isNull(depth-1)) sortArray.put(new JSONArray());

			sortArray.getJSONArray(depth-1).put(new JSONObject(menuList.get(i)));
		}
		
		
		
		for(int i=maxDepth; i>=1; i--) {
			
			JSONArray eleArray = sortArray.getJSONArray(i-1);
			
			//가장 최상위 depth에 도달했을 때
			if(i!=1) {
				
				for(int j=0; j<eleArray.length(); j++) {
					
					JSONObject eleJSON = eleArray.getJSONObject(j);
					
					JSONArray upArray = sortArray.getJSONArray(i-2);
					
					for(int k=0; k<upArray.length(); k++) {
											
						JSONObject upJSON = upArray.getJSONObject(k);
						
						if(upJSON.get("listNo").toString().equals(eleJSON.get("parentListNo").toString())) {
							
							if(upJSON.isNull("children")) upJSON.put("children", new JSONArray());
							
							((JSONArray) upJSON.get("children")).put(eleJSON);
							
						}
						
					}
					
				}
				
			}
		}
		
	}
}
