package com.web.common.resolver;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.common.util.JSONUtil;

//HandlerMethodArgumentResolver�� ����� ��û�� Controller�� �����ϱ� ���� �� ��û�� �Ķ���͵��� ������ �� �ְ� ���ִ� ������ �Ѵ�.
public class WebResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private JSONUtil jsonUtil;
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
		CommandMap commandMap = new CommandMap();
		ObjectMapper mapper = new ObjectMapper();
		
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        
        if(req.getAttribute("jsonReqInfo")!=null) {
        	
        	JSONObject jsonObject = (JSONObject) req.getAttribute("jsonReqInfo");
        	commandMap.putAll(jsonObject.toMap());
        	
        }else {
        
        	Enumeration<?> enumeration = req.getParameterNames();            
            
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
        	
        }
         
        return commandMap;
	}
	
}
