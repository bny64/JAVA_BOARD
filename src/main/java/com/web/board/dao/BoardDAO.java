package com.web.board.dao;

import javax.persistence.PersistenceException;

import com.web.board.domain.Board;

public interface BoardDAO {

	public void registBoard(Board board) throws PersistenceException;
	
}
