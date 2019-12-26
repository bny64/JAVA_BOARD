package com.web.board.service;

import javax.persistence.PersistenceException;

import com.web.board.domain.Board;

public interface BoardService {

	public void registBoard(Board board) throws PersistenceException;
	
}
