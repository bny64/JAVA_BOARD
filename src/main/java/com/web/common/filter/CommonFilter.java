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
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		
		String contentType = request.getContentType();
		
		if(contentType != null) {
			//요청이 json인 경우
			if(contentType.contains("application/json")) {
				
				StringBuffer sb = new StringBuffer(); 
				String line = null; 
				BufferedReader reader = request.getReader();
				  
				while((line=reader.readLine())!=null) { 
					sb.append(line);
				}
				
				request.setAttribute("jsonReqCheck", true);
				request.setAttribute("jsonReqInfo", new JSONObject(sb.toString()));
				
			//파일이 포함 됐을 때
			}else if(contentType.contains("multipart/form-data")){
				
				request.setAttribute("multiReqCheck", true);				
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
