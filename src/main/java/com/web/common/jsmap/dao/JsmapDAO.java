package com.web.common.jsmap.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.common.dao.CommonDAO;
import com.web.common.jsmap.domain.Jsmap;

@Repository
@Transactional
public class JsmapDAO extends CommonDAO {
	
	public List<Jsmap> getJsmap() throws PersistenceException {	
		SessionFactory sf = sessionFactory;
		CriteriaBuilder cb = sf.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Jsmap> cr = cb.createQuery(Jsmap.class);
		Root<Jsmap> root = cr.from(Jsmap.class);
		cr.multiselect(root.get("url"), root.get("type"));
		return sf.getCurrentSession().createQuery(cr).getResultList();
	}

}
