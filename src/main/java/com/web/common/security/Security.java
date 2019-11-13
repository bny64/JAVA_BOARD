package com.web.common.security;

import java.security.MessageDigest;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Security {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//salt 생성
	public String generateSalt() {
		
		logger.debug("---------- [Security]:[generateSalt] -----------");
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x",salt[i]));
        }
        
        return sb.toString();
	}
	
	//SHA256 암호화
	public String getEncrypt(String source, String strSalt) throws Exception{
		
		logger.debug("---------- [Security]:[getEncrypt] -----------");
        String result = "";
        
        byte[] a = source.getBytes();
        byte[] salt = strSalt.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);
        
        byte[] byteData = md.digest();
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
        }
        
        result = sb.toString();
        
        return result;
    }	
		
	public boolean comparePassword(String strSalt, String source, String source2) throws Exception {
		
		String encSource = getEncrypt(source, strSalt);
		
		if(encSource.equals(source2)) {
			return true;
		}else {
			return false;			
		}		
	}
	
	/*
	 * //sha256방식으로 암호화 public String hashSHA256(String password) throws Exception {
	 * logger.debug("Class:Security, Method:hashSHA256"); MessageDigest digest =
	 * MessageDigest.getInstance("SHA-256");
	 * 
	 * byte[] hash = digest.digest(password.getBytes("UTF-8")); StringBuffer
	 * hexString = new StringBuffer();
	 * 
	 * for (int i = 0; i < hash.length; i++) { String hex = Integer.toHexString(0xff
	 * & hash[i]); if (hex.length() == 1) hexString.append("0");
	 * hexString.append(hex); }
	 * 
	 * return hexString.toString(); }
	 * 
	 * public String saltSHA1(String userKey) throws Exception {
	 * logger.debug("Class:Security, Method:saltSHA1"); MessageDigest md =
	 * MessageDigest.getInstance("SHA1"); md.update(new byte[12]); byte[] bytes =
	 * md.digest(userKey.getBytes()); StringBuilder sb = new StringBuilder(); for
	 * (int i = 0; i < bytes.length; i++) { sb.append(Integer.toString((bytes[i] &
	 * 0xff) + 0x100, 16).substring(1)); }
	 * 
	 * userKey = sb.toString(); return userKey; }
	 * 
	 * public boolean compareStringHash(String str, String hash) throws Exception {
	 * logger.debug("Class:Security, Method:compareStringHash"); String str_hash =
	 * hashSHA256(str); if (str_hash == hash) { return true; } else { return false;
	 * } }
	 */
}