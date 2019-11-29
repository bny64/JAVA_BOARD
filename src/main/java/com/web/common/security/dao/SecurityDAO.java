package com.web.common.security.dao;

import java.util.List;

import javax.persistence.PersistenceException;
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

@Repository
@Transactional
public class SecurityDAO extends CommonDAO{

	public List<User> selectByEmail(String email) throws PersistenceException {
		SessionFactory sf = sessionFactory;
		//sessionFactory���� CriteriaBuilder�� �����´�.		
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		
		//CriteriaBuilder�κ��� CriteriaQuery��ü�� �����´�.
		//��ȯŸ���� �� �� ���ٸ� ���׸�Ÿ���� Object�� �ش�.
		CriteriaQuery<User> cr = cb.createQuery(User.class); //createQuery ����
		
		//��ȸ�� �������� ���ϴ� Root��ü ����(Root�� ������ ��ƼƼ�� ǥ���ϴ� ���� ǥ����)
		Root<User> root = cr.from(User.class);
		
		//�˻����� ����
		Predicate restrictions = cb.equal(root.get("email"), email);
		
		//����
		cr.select(root)
			.where(restrictions);
		
		return sf.getCurrentSession().createQuery(cr).getResultList();
	}
	
	public UserAuthority SelectAuth(String id) throws PersistenceException {
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
