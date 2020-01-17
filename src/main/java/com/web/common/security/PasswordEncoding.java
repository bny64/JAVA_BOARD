package com.web.common.security;

import java.security.MessageDigest;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoding implements PasswordEncoder {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* to be defined by bean as spring-security-context.xml file
	 * spring-security-context.xml 파일에 정의되어 있다
	 * */
	private PasswordEncoder passwordEncoder;
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	//NOT USED
	public String generateSalt() {
		
		logger.debug("---------- [Security]:[generateSalt] -----------");
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            /* to change byte to hex value
             * byte 값을 Hex 값으로 바꾸기
             * */
            sb.append(String.format("%02x",salt[i]));
        }
        
        return sb.toString();
	}
		
	/* SHA256 encryption
	 * SHA256 암호화
	 * */
	//NOT USED
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
		
	//NOT USED
	public boolean comparePassword(String strSalt, String source, String source2) throws Exception {
		
		String encSource = getEncrypt(source, strSalt);
		
		if(encSource.equals(source2)) {
			return true;
		}else {
			return false;			
		}		
	}

	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {		
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}	
}