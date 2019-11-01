package com.web.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebCommonController{

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void setReqRes(HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.response = res;
	}
		
}
