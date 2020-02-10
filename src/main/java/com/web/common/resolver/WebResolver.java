package com.web.common.resolver;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.common.menuList.MenuListService;
import com.web.common.util.ParserUtil;

//HandlerMethodArgumentResolver는 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정할 수 있게 해주는 역할을 한다.
public class WebResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private MenuListService menuListService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private ParserUtil parserUtil;
	/**
     * resolveArgument를 실행 할 수 있는 method인지 판별
     * @param methodParameter
     * @return
     */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {		
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
		
	}

	/**
     * Method parameter에 대한 Argument Resovler로직 처리
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
        
        if(req.getAttribute("jsonReqCheck")!=null && (boolean)req.getAttribute("jsonReqCheck")) {
        	
        	JSONObject jsonObject = (JSONObject) req.getAttribute("jsonReqInfo");
        	commandMap.putAll(jsonObject.toMap());
        	
        }else {
        	
        	if(req.getAttribute("multiReqCheck")!=null && (boolean) req.getAttribute("multiReqCheck")) {
        		
        		MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest) req;
        		
        		Iterator<String> fileNames = multipartReq.getFileNames();
        		
        		while(fileNames.hasNext()) {
        			String fileName = fileNames.next();
        			MultipartFile file = multipartReq.getFile(fileName);
        			commandMap.put(fileName, file);
        		}
        		
        	}
        	
        	Enumeration<?> enumeration = req.getParameterNames();            
            
            String key = null;
            String[] values = null;
            while(enumeration.hasMoreElements()) {
                
            	key = (String) enumeration.nextElement();
                        	
                if(parserUtil.validJSONByStr(key)) {
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