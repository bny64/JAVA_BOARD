package com.test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JSch jsch = new JSch();
		
		Session session;
		try {
			
			session = jsch.getSession("bny01", "49.247.211.93", 22);
			
			session.setPassword("bny01");
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			
			
			
			System.out.println(session.toString());
			
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

}
