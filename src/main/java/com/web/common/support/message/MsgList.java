package com.web.common.support.message;

import java.util.HashMap;

public class MsgList {

	private static MsgList docMessageList = null;
	private HashMap<MsgCode, String[]> msgList = new HashMap<MsgCode, String[]>();
	
	private MsgList() {
		
		String[] msg = new String[2];
		msg[0] = "S0000";
		msg[1] = "��ȸ ���� �Ͼ����ϴ�.";
		msgList.put(MsgCode.SelectSuccess, msg);
		
		msg = new String[2];
		msg[0] = "S0001";
		msg[1] = "��ȸ ���� �Ͽ����ϴ�.";
		msgList.put(MsgCode.SelectFail, msg);
		
		msg = new String[2];
		msg[0] = "I0000";
		msg[1] = "�Է� ���� �Ͽ����ϴ�.";
		msgList.put(MsgCode.InsertSuccess, msg);
		
		msg = new String[2];
		msg[0] = "I0001";
		msg[1] = "�Է� ���� �Ͽ����ϴ�.";
		msgList.put(MsgCode.InsertFail, msg);
		
		msg = new String[2];
		msg[0] = "AC0000";
		msg[1] = "��밡���� ID �Դϴ�.";
		msgList.put(MsgCode.ValidateId, msg);
		
		msg = new String[2];
		msg[0] = "AC0001";
		msg[1] = "ID�� �ߺ��˴ϴ�.";
		msgList.put(MsgCode.DuplicateId, msg);
		
		msg = new String[2];
		msg[0] = "EM0000";
		msg[1] = "��밡���� �̸��� �Դϴ�.";
		msgList.put(MsgCode.ValidateEmail, msg);
		
		msg = new String[2];
		msg[0] = "EM0001";
		msg[1] = "�̸����� �ߺ��˴ϴ�.";
		msgList.put(MsgCode.DuplicateEmail, msg);
		
		msg = new String[2];
		msg[0] = "JN0000";
		msg[1] = "���������� ���ԵǾ����ϴ�.";
		msgList.put(MsgCode.SuccessJoin, msg);
		
		msg = new String[2];
		msg[0] = "LG0001";
		msg[1] = "���̵� Ȥ�� ��й�ȣ�� ���� �ʽ��ϴ�.";
		msgList.put(MsgCode.NullUser, msg);
		
		msg = new String[2];
		msg[0] = "LG0001";
		msg[1] = "�α��� �����߽��ϴ�.";
		msgList.put(MsgCode.SuccessLogin, msg);
		
		msg = new String[2];
		msg[0] = "RQ0001";
		msg[1] = "��û �� ������ �߻������ϴ�.";
		msgList.put(MsgCode.RequestError, msg);
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
