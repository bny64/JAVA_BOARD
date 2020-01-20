package com.web.common.advice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.web.common.resolver.CommandMap;
import com.web.common.support.message.MsgCode;
import com.web.common.support.message.MsgList;

/*// 사용하기 위해서 xml에서 anotation-driven설정해야 함.*/
@ControllerAdvice 
public class CommonControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody CommandMap unAuthHandleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException{
		CommandMap map = new CommandMap();
		return map;
	} 
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CommandMap handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException{
		logger.debug("********************[CommonControllerAdvice]:[handleException]********************");	
		CommandMap comMap = new CommandMap();
		String[] msg;
		
		e.printStackTrace();
		
		String acceptHeader = request.getContentType();
		
		//asynchronous 요청일 경우
		if(acceptHeader != null) {
		
			if(acceptHeader.contains("multipart/form-data") ||
					acceptHeader.contains("application/json") ||
					acceptHeader.contains("application/x-www-form-urlencoded")) {
				
				msg = MsgList.getInstance().getCodeMessage(MsgCode.RequestError);
				comMap.put("msgCode", msg[0]);
				comMap.put("msg", msg[1]);			
				return comMap;				
			}			
		}
		
		//URL 이동일 경우
		response.sendRedirect("/common/error/requestError.do");
		return null;
	}
	
	private List<String> generate(Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		String trace = writer.getBuffer().toString();

		Pattern tracePattern = Pattern
				.compile("\\s*at\\s+([\\w\\.$_]+)\\.([\\w$_]+)(\\\\(.*java)?:(\\\\d+)\\\\)(\\\\n|\\\\r\\\\n)");
		Matcher matcher = tracePattern.matcher(trace);
		List<String> rtnData = new ArrayList<String>();
		while (matcher.find()) {
			String className = matcher.group(1);
			int lineNum = Integer.parseInt(matcher.group(4));
			String[] arrName = className.split("\\.");
			String data = String.valueOf(lineNum) + "-" + arrName[arrName.length - 1];
			rtnData.add(data);

			if (rtnData.size() > 1) {
				break;
			}
		}
		return rtnData;

	}
}