package com.web.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParserUtil<T>{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean validJSONByStr(String str) {
		logger.debug("---------- [ParserUtil]:[validJSONByStr] -----------");
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.readTree(str);
			return true;
		} catch (JsonProcessingException e) {
			return false;
		}
	}

	public Map<String, Object> nativeQueryParser(String[] columns, Object[] results) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		for (int i = 0; i < columns.length; i++) {
			returnMap.put(columns[i], results[i]);
		}

		return returnMap;

	}
	
	public String[] getClassFieldNames(Class<T> paramClass){
		
		List<String> fields = new ArrayList<String>();
		Field[] classFields = paramClass.getDeclaredFields();
		
		for(Field field : classFields) {
			fields.add(field.getName());
		}
		
		return fields.toArray(new String[0]);
	}

}
