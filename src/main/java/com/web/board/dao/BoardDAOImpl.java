package com.web.board.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.board.domain.Board;
import com.web.common.dao.CommonDAO;

@Repository
@Transactional(noRollbackFor = {Exception.class})
public class BoardDAOImpl extends CommonDAO implements BoardDAO{

	@Override
	public void registBoard(Board board) throws PersistenceException {		
		sessionFactory.getCurrentSession().save(board);		
	}

	@Override
	public List<Board> getBoardList(Map<String, String> param) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
