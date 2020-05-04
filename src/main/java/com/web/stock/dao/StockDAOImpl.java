package com.web.stock.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.web.common.dao.CommonDAO;
import com.web.stock.domain.StockData;
import com.web.stock.domain.UserStockList;

@Repository
@Transactional(noRollbackFor = {Exception.class})
public class StockDAOImpl extends CommonDAO implements StockDAO{

	@Override
	public void addNewStock(UserStockList userStockList) throws Exception {
		
		Session session = getSession();
		session.save(userStockList);
		
	}

	@Override
	public Long getStockOrder(Map<String, Object> param) throws Exception {
		
		Session session = getSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cr = cb.createQuery(Long.class);
		Root<UserStockList> root = cr.from(UserStockList.class);
		Predicate restriction = cb.equal(root.get("id"), (String)param.get("id"));

		cr.select(cb.count(root)).where(restriction);
		
		return session.createQuery(cr).getSingleResult();
	}

	@Override
	public List<UserStockList> getStockList(Map<String, Object> param) throws Exception {

		Session session = getSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserStockList> cr = cb.createQuery(UserStockList.class);
		Root<UserStockList> root = cr.from(UserStockList.class);
		Predicate restriction = cb.equal(root.get("id"), (String)param.get("id"));
		
		cr.select(cb.construct(UserStockList.class, root.get("stockName"), root.get("stockCode"), root.get("stockNickName"), root.get("stockKey")))
			.where(restriction)
			.orderBy(cb.asc(root.get("createdAt")));
		
		List<UserStockList> stockList = session.createQuery(cr).getResultList();
		
		return stockList;
	}

	@Override
	public List<StockData> getStockData(Map<String, Object> param) throws Exception {

		Session session = getSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<StockData> cr = cb.createQuery(StockData.class);
		Root<StockData> root = cr.from(StockData.class);
		
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(root.get("id"), (String)param.get("id")));
		restrictions.add(cb.equal(root.get("stockKey"), (String)param.get("stockKey")));
		
		cr.select(cb.construct(StockData.class,	root.get("accEstPrc"), root.get("accIvstPrc"), root.get("accMnt"), root.get("buySrvfee"), root.get("createdAt"), 
				root.get("ernRate"), root.get("ernRatePer"), root.get("id"), root.get("ivstPrc"), root.get("nowPrc"), root.get("sellSrvfee"), root.get("stockCode"),
				root.get("stockDate"), root.get("stockName"), root.get("taxFee"), root.get("byMnt")))
			.where(restrictions.toArray(new Predicate[] {}))
			.orderBy(cb.asc(root.get("createdAt")));
			
		List<StockData> stockList = session.createQuery(cr).getResultList();
		System.out.println(stockList);
		return stockList;
	}

	@Override
	public void addStockData(StockData stockData) throws Exception {
		
		Session session = getSession();
		session.save(stockData);
		
	}

	@Override
	public void deleteStock(Map<String, Object> param) throws Exception {
		Session session = getSession();
		
		String userId = (String) param.get("id");
		String stockKey = (String) param.get("stockKey");
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<UserStockList> dl = cb.createCriteriaDelete(UserStockList.class);
		Root<UserStockList> root = dl.from(UserStockList.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(root.get("id"), userId));
		restrictions.add(cb.equal(root.get("stockKey"), stockKey));
		
		dl.where(restrictions.toArray(new Predicate[] {}));
		session.createQuery(dl).executeUpdate();
		
	}

	@Override
	public void deleteStockData(Map<String, Object> param) throws Exception {
		Session session = getSession();
		
		String userId = (String) param.get("id");
		String stockKey = (String) param.get("stockKey");
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<StockData> dl = cb.createCriteriaDelete(StockData.class);
		Root<StockData> root = dl.from(StockData.class);
		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(cb.equal(root.get("id"), userId));
		restrictions.add(cb.equal(root.get("stockKey"), stockKey));
		
		dl.where(restrictions.toArray(new Predicate[] {}));
		session.createQuery(dl).executeUpdate();
		
		
		
	}

}
