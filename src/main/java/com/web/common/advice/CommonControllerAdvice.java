package com.web.common.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CommonControllerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
		 
	}
	
}
