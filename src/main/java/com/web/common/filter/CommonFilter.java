package com.web.common.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.json.JSONObject;
import org.springframework.core.annotation.Order;

@Order(1)
public class CommonFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//요청이 json인 경우
		if(request.getContentType() != null) {
			
			if(request.getContentType().contains("application/json")) {
				
				StringBuffer sb = new StringBuffer(); 
				String line = null; 
				BufferedReader reader = request.getReader();
				  
				while((line=reader.readLine())!=null) { 
					sb.append(line);
				}
				
				request.setAttribute("jsonReqInfo", new JSONObject(sb.toString()));
				
			}else if(request.getContentType().contains("multipart/form-data")) {
				
				request.setAttribute("multiReqInfo", true);				
			}
			
			
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
