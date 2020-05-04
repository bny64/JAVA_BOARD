package com.web.stock.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.stock.dao.StockDAO;
import com.web.stock.domain.StockData;
import com.web.stock.domain.UserStockList;

@Service
public class StockServiceImpl implements StockService{

	@Autowired
	private StockDAO stockDao;

	@Override
	public void addNewStock(UserStockList userStockList) throws Exception {
		stockDao.addNewStock(userStockList);
		
	}

	@Override
	public Long getStockOrder(Map<String, Object> param) throws Exception {		
		return stockDao.getStockOrder(param);
	}

	@Override
	public List<UserStockList> getStockList(Map<String, Object> param) throws Exception {
		return stockDao.getStockList(param);
	}

	@Override
	public List<StockData> getStockData(Map<String, Object> param) throws Exception {		
		return stockDao.getStockData(param);
	}

	@Override
	public void addStockData(StockData stockData) throws Exception {
		stockDao.addStockData(stockData);
	}

	@Override
	public void deleteStock(Map<String, Object> param) throws Exception {
		stockDao.deleteStock(param);
		
	}

	@Override
	public void deleteStockData(Map<String, Object> param) throws Exception {
		stockDao.deleteStockData(param);
		
	}
	
}
