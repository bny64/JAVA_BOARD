package com.web.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.web.auth.domain.User;
import com.web.board.domain.Board;
import com.web.board.service.BoardService;
import com.web.common.controller.WebCommonController;
import com.web.common.resolver.CommandMap;
import com.web.common.security.PasswordEncoding;
import com.web.common.support.message.MsgCode;
import com.web.common.support.message.MsgList;
import com.web.common.wrapper.MutableHttpServletRequestWrapper;
import com.web.main.controller.MainController;

@Controller
@RequestMapping(value="/board")
public class BoardController extends WebCommonController{

private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private PasswordEncoding passwordEncoding;

	@Autowired
	private BoardService boardService;
	
	//method 입력하지 않을 시 default값은 GET
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mnv) throws Exception{
		logger.debug("---------- BoardController boardList -----------");		
		mnv.setViewName("board/boardList");
		
		return mnv;
	}
	
	@RequestMapping(value="/registBoard", method = RequestMethod.GET)
	public ModelAndView writeBoard(ModelAndView mnv) throws Exception{
		logger.debug("---------- BoardController boardList -----------");		
		mnv.setViewName("board/registBoard");
		
		return mnv;
	}
	
	@RequestMapping(value="/registBoard", method=RequestMethod.POST)
	public @ResponseBody CommandMap registBoard(CommandMap reqMap) throws Exception{
		
		CommandMap comMap = new CommandMap();
		User user = null;
		Board board = new Board();
		String[] msg;
		
		String passwordYn = reqMap.get("passwordYn").toString();
		
		board.setTitle(reqMap.get("title").toString());
		board.setContents(reqMap.get("contents").toString());
		board.setViewYn(reqMap.get("viewYn").toString());
		board.setPasswordYn(passwordYn);
		
		if("Y".equals(passwordYn)) board.setPassword(passwordEncoding.encode(reqMap.get("password").toString()));
		
		user = getSessionUser();
			
		board.setUser(user);
		board.setId(user.getId());
		board.setName(user.getName());
		
		boardService.registBoard(board);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.InsertSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.POST)
	public @ResponseBody CommandMap deleteBoard(CommandMap reqMap) throws Exception{
		
		CommandMap comMap = new CommandMap();
		return comMap;
	}
	
	@RequestMapping(value="/reviseBoard", method=RequestMethod.POST)
	public @ResponseBody CommandMap reviseBoard(CommandMap reqMap) throws Exception{
		
		CommandMap comMap = new CommandMap();
		return comMap;
	}
}
