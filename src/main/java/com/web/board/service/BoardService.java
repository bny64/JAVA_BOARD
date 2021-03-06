package com.web.board.service;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import com.web.auth.domain.User;
import com.web.board.domain.Board;

public interface BoardService {
	public void registBoard(Board board) throws PersistenceException;
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException;
	public Long getNumOfBoards(Map<String, Object> param) throws PersistenceException;
	public Board getBoard(Map<String, Object> param) throws PersistenceException;
	public Board getBoard_2(Map<String, Object> param) throws PersistenceException;
	public void updateBoard(Board board) throws PersistenceException;
	public void deleteBoard(Map<String, Object> param) throws PersistenceException;
	public Board setBoard(Map<String, Object> param) throws Exception;
	public Map<String, Object> getBoardMap(Board board, User user) throws Exception;
}
