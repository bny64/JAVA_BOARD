package com.test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

public class TestClass {
	
	public static void main(String[] args) {
		try {
			FileInputStream is = new FileInputStream("properties/database.properties");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
