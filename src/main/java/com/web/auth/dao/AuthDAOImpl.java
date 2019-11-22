package com.web.auth.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.auth.domain.User;
import com.web.auth.domain.UserAuthority;
import com.web.common.dao.CommonDAO;
import com.web.common.security.CustomUserDetails;

@Repository
@Transactional//data-context에서 설정한 transactionManager를 사용하려면 애노테이션 추가해야 함. (에러 때문에 몇 시간 고생..)
public class AuthDAOImpl extends CommonDAO implements AuthDAO{
	
	//사용자 저장
	@Override
	public void join(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
	}

	//아이디로 유저 검색
	@Override
	public List<User> selectById(String id) throws Exception {		
		SessionFactory sf = sessionFactory;
		//sessionFactory에서 CriteriaBuilder를 가져온다.		
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		
		//CriteriaBuilder로부터 CriteriaQuery객체를 가져온다.
		//반환타입을 알 수 없다면 제네릭타입을 Object로 준다.
		CriteriaQuery<User> cr = cb.createQuery(User.class); //createQuery 생성
		
		//조회의 시작점을 뜻하는 Root객체 생성(Root는 영속적 엔티티를 표시하는 쿼리 표현식)
		Root<User> root = cr.from(User.class);
		
		//검색조건 정의
		Predicate restrictions = cb.equal(root.get("id"), id);
		
		//쿼리
		cr.select(root)
			.where(restrictions);
		
		return sf.getCurrentSession().createQuery(cr).getResultList();
	}

	//이메일로 유저 검색
	@Override
	public List<User> selectByEmail(String email) throws Exception {
		SessionFactory sf = sessionFactory;
		//sessionFactory에서 CriteriaBuilder를 가져온다.		
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		
		//CriteriaBuilder로부터 CriteriaQuery객체를 가져온다.
		//반환타입을 알 수 없다면 제네릭타입을 Object로 준다.
		CriteriaQuery<User> cr = cb.createQuery(User.class); //createQuery 생성
		
		//조회의 시작점을 뜻하는 Root객체 생성(Root는 영속적 엔티티를 표시하는 쿼리 표현식)
		Root<User> root = cr.from(User.class);
		
		//검색조건 정의
		Predicate restrictions = cb.equal(root.get("email"), email);
		
		//쿼리
		cr.select(root)
			.where(restrictions);
		
		return sf.getCurrentSession().createQuery(cr).getResultList();
	}

	@Override
	public UserAuthority SelectAuth(String id) throws Exception {
		SessionFactory sf = sessionFactory;
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<UserAuthority> cr = cb.createQuery(UserAuthority.class);
		Root<UserAuthority> root = cr.from(UserAuthority.class);
		Predicate restrictions = cb.equal(root.get("id"), id);
		cr.select(root)
			.where(restrictions);
		return sf.getCurrentSession().createQuery(cr).getSingleResult();
	}
	
}
