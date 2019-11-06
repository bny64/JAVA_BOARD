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
import com.web.common.dao.CommonDAO;

@Repository
@Transactional//data-context���� ������ transactionManager�� ����Ϸ��� �ֳ����̼� �߰��ؾ� ��. (���� ������ �� �ð� ����..)
public class AuthDAOImpl extends CommonDAO implements AuthDAO{
	
	//����� ����
	@Override
	public void join(User user) throws Exception {
		sessionFactory.getCurrentSession().save(user);
	}

	//���̵�� ���� �˻�
	@Override
	public List<User> selectById(String id) throws Exception {		
		SessionFactory sf = sessionFactory;
		//sessionFactory���� CriteriaBuilder�� �����´�.		
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		
		//CriteriaBuilder�κ��� CriteriaQuery��ü�� �����´�.
		//��ȯŸ���� �� �� ���ٸ� ���׸�Ÿ���� Object�� �ش�.
		CriteriaQuery<User> cr = cb.createQuery(User.class); //createQuery ����
		
		//��ȸ�� �������� ���ϴ� Root��ü ����(Root�� ������ ��ƼƼ�� ǥ���ϴ� ���� ǥ����)
		Root<User> root = cr.from(User.class);
		
		//�˻����� ����
		Predicate restrictions = cb.equal(root.get("id"), id);
		
		//����
		cr.select(root)
			.where(restrictions);
		
		return sf.getCurrentSession().createQuery(cr).getResultList();
	}

	//�̸��Ϸ� ���� �˻�
	@Override
	public User selectByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return new User();
	}
	
	
}