package com.web.stock.service;

import java.util.List;
import java.util.Map;

import com.web.stock.domain.StockData;
import com.web.stock.domain.UserStockList;

public interface StockService {
	public void addNewStock(UserStockList userStockList) throws Exception;
	public Long getStockOrder(Map<String, Object> param) throws Exception;
	public List<UserStockList> getStockList(Map<String, Object> param) throws Exception;
	public List<StockData> getStockData(Map<String, Object> param) throws Exception;
}
