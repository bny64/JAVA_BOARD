package com.web.board.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
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
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException {
	
		int page = Integer.parseInt(param.get("page").toString());
		int pageSize = Integer.parseInt(param.get("pageSize").toString());
		
		SessionFactory sf = sessionFactory;		
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Board> cr = cb.createQuery(Board.class);
		Root<Board> root = cr.from(Board.class);
				
		cr.select(cb.construct(Board.class, root.get("id"), root.get("name"), root.get("createdAt"), root.get("updatedAt"), root.get("contents"), root.get("title")))
			.orderBy(cb.desc(root.get("createdAt")));
		
		List<Board> boards = sf.getCurrentSession().createQuery(cr).setFirstResult((page-1)*pageSize).setMaxResults(page*pageSize).getResultList();
		
		return boards;
	}

}
