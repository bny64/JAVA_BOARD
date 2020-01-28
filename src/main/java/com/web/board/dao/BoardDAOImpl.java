package com.web.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.board.domain.Board;
import com.web.common.dao.CommonDAO;

@Repository
@Transactional(noRollbackFor = {Exception.class})
public class BoardDAOImpl extends CommonDAO implements BoardDAO{
	
	/* 게시판 등록  */
	@Override
	public void registBoard(Board board) throws PersistenceException {		
		
		Session session = getSession();
		session.save(board);
		
	}

	/* 게시판 리스트  */
	@Override
	public List<Board> getBoardList(Map<String, Object> param) throws PersistenceException {
		
		Session session = getSession();
		
		int page = Integer.parseInt(param.get("page").toString());
		int pageSize = Integer.parseInt(param.get("pageSize").toString());
						
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Board> cr = cb.createQuery(Board.class);
		Root<Board> root = cr.from(Board.class);
		Predicate restriction = cb.equal(root.get("viewYn"), "Y");
		
		cr.select(cb.construct(Board.class, root.get("listNo"), root.get("id"), root.get("name"), root.get("createdAt"), root.get("updatedAt"), 
				root.get("contents"), root.get("title"), root.get("imgFilePath"), root.get("fileName"), root.get("thumbImgFilePath"), 
				root.get("thumbFileName"), root.get("orgFileName"), root.get("passwordYn"), root.get("viewYn")))
			.where(restriction)
			.orderBy(cb.desc(root.get("createdAt")));		
		
		List<Board> boards = session.createQuery(cr).setFirstResult((page-1)*pageSize).setMaxResults(pageSize).getResultList();
		
		return boards;
	}

	/* 게시판 총 갯수 가져오기 */
	@Override
	public Long getNumOfBoards(Map<String, Object> param) throws PersistenceException {
		
		Session session = getSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<Board> root = cr.from(Board.class);
		Predicate restriction = cb.equal(root.get("viewYn"), "Y");
		
		cr.select(cb.count(root)).where(restriction);
		
		return session.createQuery(cr).getSingleResult();
	}

	@Override
	public Board getBoard(Map<String, Object> param) throws PersistenceException {
		
		Session session = getSession();
		
		int listNo = Integer.parseInt((String) param.get("listNo"));
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Board> cr = cb.createQuery(Board.class);
		Root<Board> root = cr.from(Board.class);
		Predicate restriction = cb.equal(root.get("listNo"), listNo);
		cr.select(root).where(restriction);
		
		return session.createQuery(cr).getSingleResult();
	}
	
	@Override
	public Board getBoard_2(Map<String, Object> param) throws PersistenceException {
		
		Session session = getSession();
		
		int listNo = Integer.parseInt((String) param.get("listNo"));
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Board> cr = cb.createQuery(Board.class);
		Root<Board> root = cr.from(Board.class);
		Predicate restriction = cb.equal(root.get("listNo"), listNo);
		cr.select(cb.construct(Board.class, root.get("listNo"), root.get("id"), root.get("name"), root.get("createdAt"), root.get("updatedAt"),
				root.get("contents"), root.get("title"), root.get("imgFilePath"), root.get("fileName"), root.get("thumbImgFilePath"),
				root.get("thumbFileName"), root.get("orgFileName"), root.get("passwordYn"), root.get("viewYn")))
		.where(restriction);
		
		return session.createQuery(cr).getSingleResult();
	}

	@Override
	public void deleteBoard(Map<String, Object> param) throws PersistenceException {
		Session session = getSession();
		
		String userId = (String) param.get("id");
		int listNo = Integer.parseInt((String) param.get("listNo")); 
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Board> dl = cb.createCriteriaDelete(Board.class);
		Root<Board> root = dl.from(Board.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(root.get("listNo"), listNo));
		restrictions.add(cb.equal(root.get("id"), userId));
		
		dl.where(restrictions.toArray(new Predicate[] {}));
		session.createQuery(dl).executeUpdate();
	}

	

}
