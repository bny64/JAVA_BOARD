package com.web.common.excpetion;

public class AccessDeniedException extends Exception{


private static final long serialVersionUID = 7627531362732540855L;

public AccessDeniedException() {}
	
	public AccessDeniedException(String msg) {
		super(msg);
	}
	
	public AccessDeniedException(Exception e) {
		super(e);
	}
	
}
