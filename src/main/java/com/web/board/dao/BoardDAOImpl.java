package com.web.board.dao;

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

}
