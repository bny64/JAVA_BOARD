package com.test;

import com.web.common.security.PasswordEncoding;

public class TestClass {

	public static void main(String[] args) {
		PasswordEncoding pe = new PasswordEncoding();
		String b = "abcd";
		String bc = pe.encode(b);
		System.out.println(b + " : " + bc);
		System.out.println(pe.matches(b, bc));
		
	}
	
}
