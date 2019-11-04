package com.web.common.security;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Security {
	
	private static Logger logger = LoggerFactory.getLogger(Security.class);

	public String hashSHA256(String password) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		byte[] hash = digest.digest(password.getBytes("UTF-8"));
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append("0");
			hexString.append(hex);
		}

		return hexString.toString();
	}

	public String saltSHA1(String userKey) throws Exception {

		logger.debug(userKey);
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(new byte[12]);
		byte[] bytes = md.digest(userKey.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		userKey = sb.toString();
		logger.debug(userKey);
		return userKey;
	}

	public boolean compareStringHash(String str, String hash) throws Exception {

		String str_hash = hashSHA256(str);
		if (str_hash == hash) {
			return true;
		} else {
			return false;
		}

	}
}
