package com.web.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

//HandlerMethodArgumentResolver는 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정할 수 있게 해주는 역할을 한다.
public class WebResolver implements HandlerMethodArgumentResolver {

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
        
        HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
        Enumeration<?> enumeration = req.getParameterNames();
         
        String key = null;
        String[] values = null;
        while(enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            values = req.getParameterValues(key);
            if(values!=null ) {
                commandMap.put(key, (values.length>1)? values : values[0]);
            }
        }
         
        return commandMap;
	}
	
}
