package com.web.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean validJSONByStr(String str) {
		logger.debug("---------- [JSONUtil]:[validJSONByStr] -----------");	
		ObjectMapper mapper = new ObjectMapper();
		
		try{
			mapper.readTree(str);
			return true;
		}catch(JsonProcessingException e) {
			return false;
		}		
		
	}
}
