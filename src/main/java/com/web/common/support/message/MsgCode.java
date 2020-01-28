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
	 * 업데이트 성공
	 * @see
	 * U0000
	 * */	
	UpdateSuccess,
	
	/**
	 * 업데이트 실패
	 * @see
	 * U0001
	 * */	
	UpdateFail,
	
	/**
	 * 삭제 성공
	 * @see
	 * D0000
	 * */	
	DeleteSuccess,
	
	/**
	 * 삭제 실패
	 * @see
	 * D0001
	 * */	
	DeleteFail,
	
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
	 * 조회된 데이터가 없음
	 * @see
	 * S0002
	 * */
	SelectNonResult,
	
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
	RequestError,
	
	/**
	 * 사용자 인증 전 에러
	 * @see
	 * UA0001
	 * */
	UnauthorizedError,
	
	/**
	 * 사용자 인증 후 에러
	 * @see
	 * AD0001
	 * */
	AccessDeniedError
}