package com.web.board.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import com.web.board.domain.Board;

public interface BoardDAO {

	public void registBoard(Board board) throws PersistenceException;
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException;
	public Long getNumOfBoards(Map<String, Object> param) throws PersistenceException;
	public Board getBoard(String listNo) throws PersistenceException;
}
