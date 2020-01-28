package com.web.board.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
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
	/*게시판 리스트:GET*/
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public ModelAndView boardList(ModelAndView mnv) throws Exception{
		logger.debug("********************[BoardController]:[boardList:GET]********************");						
		mnv.setViewName("board/boardList");
		return mnv;
	}
	
	/*게시판 글쓰기:GET*/
	@RequestMapping(value="/registBoard", method = RequestMethod.GET)
	public ModelAndView registBoard(ModelAndView mnv) throws Exception{
		logger.debug("********************[BoardController]:[registBoard:GET]********************");		
		mnv.setViewName("board/registBoard");
		return mnv;
	}

	/*게시판 수정하기:GET*/
	@RequestMapping(value="/modifyBoard", method = RequestMethod.GET)
	public ModelAndView modifyBoard(ModelAndView mnv, CommandMap reqMap) throws Exception{
		logger.debug("********************[BoardController]:[modifyBoard:GET]********************");	
		mnv.setViewName("board/modifyBoard");
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		Board board = boardService.getBoard(reqMap.getMap());
		
		
		
		return mnv;
	}
	
	/*게시판 보기 비밀번호 체크:GET*/
	
	  @RequestMapping(value="/viewBoardCheck", method = RequestMethod.GET) 
	  public ModelAndView viewBoardCheck(ModelAndView mnv, CommandMap reqMap) throws Exception {
		  logger.debug("********************[BoardController]:[viewBoardCheck:GET]********************");
		  mnv.setViewName("board/viewBoardCheck"); 
		  
		  mnv.addObject("listNo", reqMap.get("listNo"));
		  
		  return mnv;
	  
	  }
	 
	
	/*게시판 보기:GET&POST*/
	@RequestMapping(value="/viewBoard")
	public ModelAndView viewBoard(ModelAndView mnv, CommandMap reqMap, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("********************[BoardController]:[modifyBoard:GET&POST]********************");	
				
		String method = request.getMethod();
		Map<String, Object> resMap = new HashMap<String, Object>();
		String listNo = (String) reqMap.get("listNo");
		Board board = new Board();
		
		board = boardService.getBoard(reqMap.getMap());
		
		//비밀번호 사용하는 게시글 인 경우
		if("Y".equals(board.getPasswordYn())){
			
			String password = (String) reqMap.get("password");
			
			//패스워드가 없을 때
			if(password==null) {
				
				response.sendRedirect("/board/viewBoardCheck.do?listNo=" + listNo);
				return null;
				
			}else if(password!=null && method.toUpperCase().equals("POST")){
				
				if(!passwordEncoding.matches(password, board.getPassword())) {
										
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href = '/board/viewBoardCheck.do?listNo=" + listNo + "';</script>");
					return null;
					
				}
				
			}else {
								
				response.sendRedirect("/board/boardList.do");
				response.getWriter().println("<script>alert('잘못된 접근입니다.')</script>");
				return null;
				
			}
		
		}
		
		mnv.setViewName("board/viewBoard");
		
		resMap.put("listNo", board.getListNo());
		resMap.put("name", board.getName());
		resMap.put("id", board.getId());
		resMap.put("title", board.getTitle());
		resMap.put("contents", board.getContents());
		
		String boardDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(board.getCreatedAt());
		resMap.put("createdAt", boardDate);
		
		String sessionId = getSessionUser().getId();
		if(sessionId.equals(board.getId())) resMap.put("equalUserYn", "Y");
		else resMap.put("equalUserYn", "N");
		
		mnv.addObject("board", resMap);
				
		return mnv;
	}
	
	/*게시판 등록하기:POST*/
	@RequestMapping(value="/registBoard", method=RequestMethod.POST)
	public @ResponseBody CommandMap registBoard(CommandMap reqMap) throws Exception{
		logger.debug("********************[BoardController]:[registBoard:POST]********************");	
		CommandMap comMap = new CommandMap();
		User user = null;
		Board board = new Board();
		String[] msg;
		List<String> fileFullPaths = new ArrayList<String>();
		List<String> fileNames = new ArrayList<String>();
		
		String passwordYn = reqMap.get("passYn").toString();
		
		if("Y".equals(passwordYn)) {
			board.setPassword(passwordEncoding.encode(reqMap.get("boardPass").toString()));
		}
		
		board.setTitle(reqMap.get("title").toString());
		board.setContents(reqMap.get("contents").toString());
		board.setViewYn(reqMap.get("viewYn").toString());
		board.setPasswordYn(passwordYn);
		
		user = getSessionUser();
			
		board.setUser(user);
		board.setId(user.getId());
		board.setName(user.getName());		
		 
		//파일 저장
		List<HashMap<String, String>> fileList = fileUtil.saveDateFiles("1", reqMap.getMap());
		
		//게시판 이미지는 한 개만 저장 가능
		if(fileList.size() > 0) {
			
			String fileFullPath = fileList.get(0).get("imgFileFullPath");
			String fileName = fileList.get(0).get("fileName");
			
			board.setFileName(fileName);
			board.setImgFilePath(fileList.get(0).get("imgFilePath"));	
			board.setOrgFileName(fileList.get(0).get("orgFileName"));
			
			fileFullPaths.add(fileFullPath);
			fileNames.add(fileName);
		}
		
		fileList.clear();
		
		reqMap.put("fileFullPaths", fileFullPaths);		
		reqMap.put("fileNames", fileNames);
		
		fileList = fileUtil.saveDateThumbFiles("thumb_1", reqMap.getMap());
		
		if(fileList.size()>0) {
			
			board.setThumbFileName(fileList.get(0).get("thumbFileName"));
			board.setThumbImgFilePath(fileList.get(0).get("thumbImgFilePath"));
			
		}
		
		boardService.registBoard(board);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.InsertSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
	/*게시판 리스트:POST*/
	@RequestMapping(value="/boardList",  method=RequestMethod.POST)
	public @ResponseBody CommandMap boardList(CommandMap reqMap) throws Exception {
		logger.debug("********************[BoardController]:[boardList:POST]********************");
		CommandMap comMap = new CommandMap();
		String[] msg;
		String thumbPath = fileUtil.parseName("thumbUrl_1");
		
		//추후 옵션값 추가 될 예정. 비밀번호, 보이기 옵션 등	
		List<Board> boards = boardService.getBoardList(reqMap.getMap());
		
		for(int i=0; i<boards.size(); i++) {
			
			Board board = boards.get(i);
			String boardThumbPath = board.getThumbImgFilePath();
			
			if(!StringUtils.isNullOrEmpty(boardThumbPath)) { //썸네일 경로가 있으면
				board.setThumbImgFilePath(thumbPath + boardThumbPath);
			}else { //썸네일 경로가 없으면
				board.setThumbImgFilePath(thumbPath);
				board.setThumbFileName("defaultThumb.jpg");
			}
			
		}
		
		comMap.put("boards", boards);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
	/*게시판 리스트 총 갯수:POST*/
	@RequestMapping(value="/getNumOfBoards", method=RequestMethod.POST)
	public @ResponseBody CommandMap getNumOfBoards(CommandMap reqMap) throws Exception{
		logger.debug("********************[BoardController]:[getNumOfBoards:POST]********************");
		CommandMap comMap = new CommandMap();
		String[] msg;
		
		Long boardCnt = boardService.getNumOfBoards(reqMap.getMap());
		comMap.put("boardCnt", boardCnt);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
		
	}
	
	/*게시판 수정하기:POST*/
	@RequestMapping(value="/modifyBoard", method=RequestMethod.POST)
	public @ResponseBody CommandMap modifyBoard(CommandMap reqMap) throws Exception{
		logger.debug("********************[BoardController]:[modifyBoard:POST]********************");
		CommandMap comMap = new CommandMap();
		return comMap;
	}
	
	/*게시판 삭제하기:POST*/
	@RequestMapping(value="/deleteBoard", method=RequestMethod.POST)
	public @ResponseBody CommandMap deleteBoard(CommandMap reqMap) throws Exception{
		logger.debug("********************[BoardController]:[deleteBoard:POST]********************");
		CommandMap comMap = new CommandMap();		
		String[] msg;
		
		reqMap.put("id", getSessionUser().getId());
		boardService.deleteBoard(reqMap.getMap());
				
		msg = MsgList.getInstance().getCodeMessage(MsgCode.DeleteSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
}