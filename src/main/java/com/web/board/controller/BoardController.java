package com.web.board.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.auth.domain.User;
import com.web.board.domain.Board;
import com.web.board.service.BoardService;
import com.web.common.controller.WebCommonController;
import com.web.common.resolver.CommandMap;
import com.web.common.security.PasswordEncoding;
import com.web.common.support.message.MsgCode;
import com.web.common.support.message.MsgList;
import com.web.common.util.FileUtil;
import com.web.main.controller.MainController;

@Controller
@RequestMapping(value="/board")
public class BoardController extends WebCommonController{

	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private PasswordEncoding passwordEncoding;

	@Autowired
	private FileUtil fileUtil;
	
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
		
		String passwordYn = reqMap.get("passYn").toString();
		if("Y".equals(passwordYn)) board.setPassword(passwordEncoding.encode(reqMap.get("password").toString()));
		
		board.setTitle(reqMap.get("title").toString());
		board.setContents(reqMap.get("contents").toString());
		board.setViewYn(reqMap.get("viewYn").toString());
		board.setPasswordYn(passwordYn);
		
		user = getSessionUser();
			
		board.setUser(user);
		board.setId(user.getId());
		board.setName(user.getName());
		
		List<HashMap<String, String>> fileList = fileUtil.saveFiles("1", reqMap.getMap());
		
		//게시판 이미지는 한 개만 저장 가능
		if(fileList.size()>0) {
			board.setImgFilePath(fileList.get(0).get("imgFilePath"));		
			board.setFileName(fileList.get(0).get("fileName"));		
			board.setOrgFileName(fileList.get(0).get("orgFileName"));
		}		
		
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