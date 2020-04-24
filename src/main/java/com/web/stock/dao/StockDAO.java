package com.web.stock.dao;

import java.util.List;
import java.util.Map;

import com.web.stock.domain.UserStockList;

public interface StockDAO {
	public void addNewStock(UserStockList userStockList) throws Exception;
	public Long getStockOrder(Map<String, Object> param) throws Exception;
	public List<UserStockList> getStockList(Map<String, Object> param) throws Exception;
}
