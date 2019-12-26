package com.web.board.service;

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

}
