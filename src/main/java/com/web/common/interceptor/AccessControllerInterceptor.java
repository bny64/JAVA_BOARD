package com.web.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.common.menuList.MenuListService;

public class AccessControllerInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private MenuListService menuListService;
		
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		String contentType = request.getContentType();
		
		if(contentType==null) {

			List<Map<String, Object>> menuList = getMenuNames(request);			
			modelAndView.addObject("menuList", menuList);
			
		}
		
	}
	
	public List<Map<String, Object>> getMenuNames(HttpServletRequest request) {
		
		String[] urls = request.getRequestURI().split("/");
		List<String> urlList = new ArrayList<String>();
		
		for(int i=1; i<urls.length; i++) {
			urlList.add("/"+urls[i].replace(".do", ""));
		}
		
		return menuListService.getMenuList(urlList);
	}
}
