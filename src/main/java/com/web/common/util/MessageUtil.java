package com.web.common.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;

public class MessageUtil {
	
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
	public Map<String, String> getMessage() {		
		Map<String, String> msg = (Map) this.parser.parseExpression(securityMsg).getValue();
		return msg;
	}
	
}
