package com.web.common.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.common.menuList.MenuListService;

public class AccessControllerInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private MenuListService menuListService;
		
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		String contentType = request.getContentType();
		
		if(contentType==null && modelAndView != null) {
			
			List<String> setMenuList = getMenuNames(request);
			List<Map<String, Object>> menuList = menuListService.getMenuList(setMenuList);			
			modelAndView.addObject("menuList", menuList);
			
			//if(!request.getRequestURI().contains("/auth")) {
				
				List<Map<String, Object>> menuListAll = menuListService.getMenuListAll();
				Map<String, Object> menuTree = setMenuList(menuListAll);
				
				modelAndView.addObject("menuTree", menuTree);
				
			//}
			
		}
		
	}
	
	public List<String> getMenuNames(HttpServletRequest request) {
		
		String[] urls = request.getRequestURI().split("/");
		List<String> urlList = new ArrayList<String>();
		
		for(int i=1; i<urls.length; i++) {
			urlList.add("/"+urls[i].replace(".do", ""));
		}
		
		return urlList;
	}
	
	public Map<String, Object> setMenuList(List<Map<String, Object>> menuList) {
		
		JSONArray sortArray = new JSONArray();
		JSONObject resultJSON = new JSONObject();
		
		int maxDepth = (int) Collections.max(menuList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {

				int o1Dep = (int) o1.get("depth");
				int o2Dep = (int) o2.get("depth");
				int result = 0;
				
				if(o1Dep < o2Dep) result = -1;
				else if(o1Dep > o2Dep) result = 1;
				return result;
			}
			
		}).get("depth");
		
		Collections.sort(menuList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				
				int o1Dep = (int) o1.get("depth");
				int o2Dep = (int) o2.get("depth");
				int result = 0;
				
				if(o1Dep < o2Dep) result = -1;
				else if(o1Dep > o2Dep) result = 1;
				return result;
				
			}
		});
		
		for (int i = 0; i < menuList.size(); i++) {

			if("N".equals(menuList.get(i).get("viewYn"))) continue;
			
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
		
		resultJSON = sortArray.getJSONArray(0).getJSONObject(0);
		
		
		
		return resultJSON.toMap();
	}
	
}
