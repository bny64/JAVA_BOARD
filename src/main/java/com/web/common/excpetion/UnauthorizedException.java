package com.web.common.excpetion;

/**
 * unauthorizedException
 * 인증이 안됐을 때 발생하는 예외
 * */
public class UnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3357513702874735569L;

	public UnauthorizedException() {}
	
	public UnauthorizedException(String msg) {
		super(msg);
	}
	
	public UnauthorizedException(Exception e) {
		super(e);
	}
	
}
