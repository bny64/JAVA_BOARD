package com.web.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.auth.domain.User;
import com.web.common.controller.WebCommonController;
import com.web.common.resolver.CommandMap;
import com.web.common.support.message.MsgCode;
import com.web.common.support.message.MsgList;
import com.web.stock.domain.UserStockList;
import com.web.stock.service.StockService;

@Controller
@RequestMapping(value="/stock")
public class StockController extends WebCommonController{

	private Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/earningsRate", method=RequestMethod.GET)
	public ModelAndView earningsRate(ModelAndView mnv, CommandMap reqMap) throws Exception {
		logger.debug("********************[StockController]:[earningsRate:GET]********************");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		User user = getSessionUser();
		String id = user.getId();
		
		paramMap.put("id", id);
		List<UserStockList> stockList = stockService.getStockList(paramMap);
		
		mnv.addObject("userStockList", stockList);
		mnv.setViewName("stock/earningsRate");
		
		return mnv;
		
	}
	
	@RequestMapping(value = "/addNewStock", method=RequestMethod.POST)
	public @ResponseBody CommandMap addNewStock(ModelAndView mnv, CommandMap reqMap) throws Exception {
		logger.debug("********************[StockController]:[addNewStock:POST]********************");
		CommandMap comMap = new CommandMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String[] msg;
		User user = null;
		UserStockList userStockList = new UserStockList();
		
		user = getSessionUser();
		userStockList.setUser(user);
		
		userStockList.setId(user.getId());
		userStockList.setName(user.getName());
		
		paramMap.put("id", user.getId());
		
		long stockOrder = stockService.getStockOrder(paramMap);
		stockOrder++;
		
		userStockList.setStockCode((String)reqMap.get("stockCode"));
		userStockList.setStockName((String)reqMap.get("stockName"));
		userStockList.setStockOrder((int) stockOrder);
		
		stockService.addNewStock(userStockList);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.InsertSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
}
