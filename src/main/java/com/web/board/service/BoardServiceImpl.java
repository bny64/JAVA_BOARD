package com.web.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.auth.domain.User;
import com.web.board.dao.BoardDAO;
import com.web.board.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public void registBoard(Board board) throws PersistenceException {
		boardDao.registBoard(board);
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException {
		return boardDao.getBoardList(param);		
	}

	@Override
	public Long getNumOfBoards(Map<String, Object> param) throws PersistenceException {		
		return boardDao.getNumOfBoards(param);
	}

	@Override
	public Board getBoard(Map<String, Object> param) throws PersistenceException {		
		return boardDao.getBoard(param);
	}

	@Override
	public Board getBoard_2(Map<String, Object> param) throws PersistenceException {		
		return boardDao.getBoard_2(param);
	}
	
	@Override
	public void updateBoard(Board board) throws PersistenceException {
		boardDao.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(Map<String, Object> param) throws PersistenceException {
		boardDao.deleteBoard(param);
	}

	//작업중
	@Override
	public Board setBoard(Map<String, Object> param) throws Exception {
		
		Board board = new Board();
		User user = (User) param.get("user");
		
		board.setUser(user);
		
		board.setPassword(((String)param.get("password")));
		board.setTitle(((String)param.get("title")));
		board.setContents(((String)param.get("contents")));
		board.setViewYn(((String)param.get("viewYn")));
		
		board.setId(user.getId());
		board.setName(user.getName());
		
		board.setFileName((String) param.get("fileName"));
		board.setImgFilePath((String) param.get("ImgFilePath"));
		board.setOrgFileName((String) param.get("orgFileName"));
		
		return board;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getBoardMap(Board board, User user) throws Exception {
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		
		board.setUser(user);
		
		ObjectMapper objectMapper = new ObjectMapper();
		boardMap = objectMapper.convertValue(board, Map.class);
		
		return boardMap;
	}

	

}
