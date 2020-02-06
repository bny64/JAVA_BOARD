package com.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

import com.web.common.wrapper.MutableHttpServletRequestWrapper;

@Order(2)
public class CustomFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		MutableHttpServletRequestWrapper MutableServletRequest = new MutableHttpServletRequestWrapper((HttpServletRequest)request);
		
		chain.doFilter(MutableServletRequest, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
