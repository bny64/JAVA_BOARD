package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TestClass {
	
	public static void main(String[] args) {
		
		String[] str = {"30001", "30002", "30003", "30004", "30005", "3000", "30007", "3000111"};
		
		Pattern pattern = Pattern.compile("^3000[1|2|3|4|5]{1}$");
		
		
		for(String strr : str) {
			Matcher matcher = pattern.matcher(strr);
			System.out.println(matcher.find());
		}		
	}
}
