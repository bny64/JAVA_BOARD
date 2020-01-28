package com.web.board.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void deleteBoard(Map<String, Object> param) throws PersistenceException {
		boardDao.deleteBoard(param);
	}

}
