package com.web.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.web.main.controller.MainController;

@Controller
@RequestMapping(value="/board")
public class BoardController {

private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	//method 입력하지 않을 시 default값은 GET
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mnv) {
		logger.debug("---------- BoardController boardList -----------");		
		mnv.setViewName("board/boardList");
		
		return mnv;
	}
}
