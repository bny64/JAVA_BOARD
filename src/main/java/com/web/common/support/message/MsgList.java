package com.web.common.support.message;

import java.util.HashMap;

public class MsgList {

	private static MsgList docMessageList = null;
	private HashMap<MsgCode, String[]> msgList = new HashMap<MsgCode, String[]>();
	
	private MsgList() {
		
		String[] msg = new String[2];
		msg[0] = "S0000";
		msg[1] = "조회 성공 하었습니다.";
		msgList.put(MsgCode.SelectSuccess, msg);
		
		msg = new String[2];
		msg[0] = "S0001";
		msg[1] = "조회 실패 하였습니다.";
		msgList.put(MsgCode.SelectFail, msg);
		
		msg = new String[2];
		msg[0] = "I0000";
		msg[1] = "입력 성공 하였습니다.";
		msgList.put(MsgCode.InsertSuccess, msg);
		
		msg = new String[2];
		msg[0] = "I0001";
		msg[1] = "입력 실패 하였습니다.";
		msgList.put(MsgCode.InsertFail, msg);
		
		msg = new String[2];
		msg[0] = "AC0000";
		msg[1] = "사용가능한 ID 입니다.";
		msgList.put(MsgCode.ValidateId, msg);
		
		msg = new String[2];
		msg[0] = "AC0001";
		msg[1] = "ID가 중복됩니다.";
		msgList.put(MsgCode.DuplicateId, msg);
		
		msg = new String[2];
		msg[0] = "EM0000";
		msg[1] = "사용가능한 이메일 입니다.";
		msgList.put(MsgCode.ValidateEmail, msg);
		
		msg = new String[2];
		msg[0] = "EM0001";
		msg[1] = "이메일이 중복됩니다.";
		msgList.put(MsgCode.DuplicateEmail, msg);
		
		msg = new String[2];
		msg[0] = "JN0000";
		msg[1] = "정상적으로 가입되었습니다.";
		msgList.put(MsgCode.SuccessJoin, msg);
		
		msg = new String[2];
		msg[0] = "LG0001";
		msg[1] = "아이디 혹은 비밀번호가 맞지 않습니다.";
		msgList.put(MsgCode.NullUser, msg);
		
		msg = new String[2];
		msg[0] = "LG0001";
		msg[1] = "로그인 성공했습니다.";
		msgList.put(MsgCode.SuccessLogin, msg);
		
		msg = new String[2];
		msg[0] = "RQ0001";
		msg[1] = "요청 중 에러가 발견됐습니다.";
		msgList.put(MsgCode.RequestError, msg);
		
		msg = new String[2];
		msg[0] = "UA0001";
		msg[1] = "인증이 되지 않은 요청입니다.";
		msgList.put(MsgCode.UnauthorizedError, msg);
		
		
		msg = new String[2];
		msg[0] = "AD0001";
		msg[1] = "권한이 없는 요청입니다.";
		msgList.put(MsgCode.AccessDeniedError, msg);
		
	}
	
	public static MsgList getInstance() {
		if(docMessageList==null) {
			docMessageList=new MsgList();
		}
		return docMessageList;
	}
	
	public String[] getCodeMessage(MsgCode docCode){
		return msgList.get(docCode);
	}
	
	public String getCode(MsgCode docCode){
		String[] code = msgList.get(docCode);
		return code[0];
	}
	
	public String getMessage(MsgCode docCode){
		String[] msg = msgList.get(docCode);
		return msg[1];
	}
}