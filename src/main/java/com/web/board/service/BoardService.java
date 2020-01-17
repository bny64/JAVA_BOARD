package com.web.board.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import com.web.board.domain.Board;

public interface BoardService {
	public void registBoard(Board board) throws PersistenceException;
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException;
	public Long getNumOfBoards(Map<String, Object> param) throws PersistenceException;
}
