package com.web.common.resolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.codec.multipart.MultipartHttpMessageReader;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.common.util.JSONUtil;

//HandlerMethodArgumentResolver�� ����� ��û�� Controller�� �����ϱ� ���� �� ��û�� �Ķ���͵��� ������ �� �ְ� ���ִ� ������ �Ѵ�.
public class WebResolver implements HandlerMethodArgumentResolver {

	/**
     * resolveArgument�� ���� �� �� �ִ� method���� �Ǻ�
     * @param methodParameter
     * @return
     */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {		
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
		
	}

	/**
     * Method parameter�� ���� Argument Resovler���� ó��
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		JSONUtil jsonUtil = new JSONUtil();
		CommandMap commandMap = new CommandMap();
		
        HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
                
        Enumeration<?> enumeration = req.getParameterNames();
        ObjectMapper mapper = new ObjectMapper();
        
        String key = null;
        String[] values = null;
        while(enumeration.hasMoreElements()) {
            
        	key = (String) enumeration.nextElement();
                    	
            if(jsonUtil.validJSONByStr(key)) {
            	Map<String, String> map = mapper.readValue(key, new TypeReference<Map<String, String>>(){});
            	for(Map.Entry<String, String> entry : map.entrySet()) {
            		commandMap.put(entry.getKey(), entry.getValue());
            	}
            }else {
            	values = req.getParameterValues(key);
            	
            	if(values!=null && !"".equals(values[0])) {
                    commandMap.put(key, (values.length>1)? values : values[0]);
                }
            }
        }
         
        return commandMap;
	}
	
}
