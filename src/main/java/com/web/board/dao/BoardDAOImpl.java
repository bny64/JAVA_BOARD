package com.web.board.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.board.domain.Board;
import com.web.common.dao.CommonDAO;

@Repository
@Transactional(noRollbackFor = {Exception.class})
public class BoardDAOImpl extends CommonDAO implements BoardDAO{
	
	@Override
	public void registBoard(Board board) throws PersistenceException {		
		Session session = getSession();
		session.save(board);		
	}

	@Override
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException {
		Session session = getSession();
		
		int page = Integer.parseInt(param.get("page").toString());
		int pageSize = Integer.parseInt(param.get("pageSize").toString());
						
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Board> cr = cb.createQuery(Board.class);
		Root<Board> root = cr.from(Board.class);
				
		cr.select(cb.construct(Board.class, root.get("id"), root.get("name"), root.get("createdAt"), root.get("updatedAt"), root.get("contents"), 
				root.get("title"), root.get("imgFilePath"), root.get("fileName"), root.get("thumbImgFilePath"), root.get("thumbFileName"), root.get("orgFileName")))
			.orderBy(cb.desc(root.get("createdAt")));		
		
		List<Board> boards = session.createQuery(cr).setFirstResult((page-1)*pageSize).setMaxResults(pageSize).getResultList();
		
		return boards;
	}

	@Override
	public Long getNumOfBoards(Map<String, Object> param) throws PersistenceException {
		
		Session session = getSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<Board> root = cr.from(Board.class);
		
		cr.select(cb.count(root));
		
		return session.createQuery(cr).getSingleResult();
	}

}
