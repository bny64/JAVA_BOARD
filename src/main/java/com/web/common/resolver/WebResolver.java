package com.web.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
