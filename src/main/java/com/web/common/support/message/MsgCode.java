package com.web.common.support.message;

public enum MsgCode {
	
	/**
	 * 입력 성공
	 * @see
	 * I0000
	 * */
	InsertSuccess,
	
	/**
	 * 입력 실패
	 * @see
	 * I0001
	 * */	
	InsertFail,
	
	/**
	 * 조회 성공
	 * @see
	 * S0000
	 * */
	SelectSuccess,
	
	/**
	 * 조회 실패
	 * @see
	 * S0001
	 * */
	SelectFail,
	
	/**
	 * id 사용가능
	 * @see
	 * AC0000
	 * */
	ValidateId,
	
	/**
	 * id 중복
	 * @see
	 * AC0001
	 * */
	DuplicateId,
	
	/**
	 * email 사용가능
	 * @see
	 * EM0000
	 * */
	ValidateEmail,
	
	/**
	 * email 중복
	 * @see
	 * EM0001
	 * */
	DuplicateEmail,
	
	/**
	 * 가입 성공
	 * @see
	 * JN0000
	 * */
	SuccessJoin,
	
	/**
	 * 로그인 성공
	 * @see
	 * LG0000
	 * */
	SuccessLogin,
	
	/**
	 * 해당 사용자 없음
	 * @see
	 * LG0001
	 * */
	NullUser,
	
	/**
	 * 요청 중 에러
	 * @see
	 * RQ0001
	 * */
	RequestError
}