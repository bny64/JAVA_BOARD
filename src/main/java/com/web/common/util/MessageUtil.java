package com.web.common.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.ExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	
	/*
	 * @Value("${security.message}")
	 * private String securityMsg;
	 * private static Ÿ�� ���� -> �� ������ @Value ����� �� ����. null�� ����
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
		
		Map<String, String> msg = (Map) this.parser.parseExpression(securityMsg).getValue();
		
		return msg.get(msgCode);
	}
	
}