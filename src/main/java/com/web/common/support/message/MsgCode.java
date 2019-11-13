package com.web.common.support.message;

public enum MsgCode {
	
	/**
	 * �Է� ����
	 * @see
	 * I0000
	 * */
	InsertSuccess,
	
	/**
	 * �Է� ����
	 * @see
	 * I0001
	 * */	
	InsertFail,
	
	/**
	 * ��ȸ ����
	 * @see
	 * S0000
	 * */
	SelectSuccess,
	
	/**
	 * ��ȸ ����
	 * @see
	 * S0001
	 * */
	SelectFail,
	
	/**
	 * id ��밡��
	 * @see
	 * AC0000
	 * */
	ValidateId,
	
	/**
	 * id �ߺ�
	 * @see
	 * AC0001
	 * */
	DuplicateId,
	
	/**
	 * email ��밡��
	 * @see
	 * EM0000
	 * */
	ValidateEmail,
	
	/**
	 * email �ߺ�
	 * @see
	 * EM0001
	 * */
	DuplicateEmail,
	
	/**
	 * ���� ����
	 * @see
	 * JN0000
	 * */
	SuccessJoin,
	
	/**
	 * �α��� ����
	 * @see
	 * LG0000
	 * */
	SuccessLogin,
	
	/**
	 * �ش� ����� ����
	 * @see
	 * LG0001
	 * */
	NullUser
	
}
