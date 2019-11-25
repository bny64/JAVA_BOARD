package com.test;

import java.io.FileReader;

public class TestClass {
	
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("/properties/database-context.xml");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
