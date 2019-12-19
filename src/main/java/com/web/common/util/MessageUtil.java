package com.web.common.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.ExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * @Value("${security.message}")
	 * private String securityMsg;
	 * private static 타입 변수 -> 이 형식은 @Value 사용할 수 없음. null값 나옴
	 */
	@Value("${security.message}")
	private String securityMsg;
	
	private ExpressionParser parser;
	
	public ExpressionParser getParser() {
		return parser;
	}

	public void setParser(ExpressionParser parser) {
		this.parser = parser;
	}

	@SuppressWarnings("unchecked")
	public String getMessage(String msgCode) {		
		logger.debug("---------- [MessageUtil]:[getMessage] -----------");	
		Map<String, String> msg = (Map) this.parser.parseExpression(securityMsg).getValue();
		
		return msg.get(msgCode);
	}
	
}
