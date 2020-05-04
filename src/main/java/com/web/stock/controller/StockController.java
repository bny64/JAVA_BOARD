package com.web.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.web.stock.domain.StockData;
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
		
		mnv.setViewName("stock/earningsRate");
		return mnv;
		
	}
	
	//종목추가
	@RequestMapping(value = "/getUserStockList", method=RequestMethod.POST)
	public @ResponseBody CommandMap getUserStockList(ModelAndView mnv, CommandMap reqMap) throws Exception {
		
		CommandMap comMap = new CommandMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String[] msg;
		User user = getSessionUser();
		String id = user.getId();
		
		paramMap.put("id", id);
		List<UserStockList> stockList = stockService.getStockList(paramMap);
		
		comMap.put("userStockList", stockList);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
	//종목추가
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
		userStockList.setStockNickName((String)reqMap.get("stockNickName"));
		userStockList.setSellCheck("N");
		userStockList.setStockKey(UUID.randomUUID().toString());
		userStockList.setStockOrder((int) stockOrder);
		
		stockService.addNewStock(userStockList);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.InsertSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
	//종목 데이터 불러오기
	@RequestMapping(value = "/getStockData", method=RequestMethod.POST)
	public @ResponseBody CommandMap getStockData(ModelAndView mnv, CommandMap reqMap) throws Exception {
		CommandMap comMap = new CommandMap();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String[] msg;
		
		paramMap.put("id", getSessionUser().getId());
		paramMap.put("stockKey", reqMap.get("stockKey"));
		
		List<StockData> stockDataList = stockService.getStockData(paramMap);
		comMap.put("stockDataList", stockDataList);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.SelectSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
	//종목 데이터 추가
	@RequestMapping(value = "/addStockData", method=RequestMethod.POST)
	public @ResponseBody CommandMap addStockData(ModelAndView mnv, CommandMap reqMap) throws Exception {
		
		CommandMap comMap = new CommandMap();
		StockData stockData = new StockData();		
		String[] msg;
		
		
		stockData.setUser(getSessionUser());		
		stockData.setId(getSessionUser().getId());
		stockData.setStockCode((String)reqMap.get("stockCode"));
		stockData.setStockName((String)reqMap.get("stockName"));
		stockData.setStockDate((String)reqMap.get("stockDate"));
		stockData.setNowPrc((String)reqMap.get("nowPrc"));
		stockData.setByMnt((String)reqMap.get("byMnt"));
		stockData.setAccMnt((String)reqMap.get("accMnt"));
		stockData.setIvstPrc((String)reqMap.get("ivstPrc"));
		stockData.setAccIvstPrc((String)reqMap.get("accIvstPrc"));
		stockData.setBuySrvfee((String)reqMap.get("buySrvfee"));
		stockData.setSellSrvfee((String)reqMap.get("sellSrvfee"));
		stockData.setTaxFee((String)reqMap.get("taxFee"));
		stockData.setAccEstPrc((String)reqMap.get("accEstPrc"));
		stockData.setErnRatePer((String)reqMap.get("ernRatePer"));
		stockData.setErnRate((String)reqMap.get("ernRate"));
		
		stockService.addStockData(stockData);
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.InsertSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
		
	}
	
	//종목 삭제
	@RequestMapping(value = "/deleteStock", method=RequestMethod.POST)
	public @ResponseBody CommandMap deleteStock(ModelAndView mnv, CommandMap reqMap) throws Exception {
		
		CommandMap comMap = new CommandMap();
		String[] msg;
		User user = getSessionUser();
		
		reqMap.put("id", user.getId());
		
		stockService.deleteStock(reqMap.getMap());
		stockService.deleteStockData(reqMap.getMap());
		
		msg = MsgList.getInstance().getCodeMessage(MsgCode.DeleteSuccess);
		comMap.put("msgCode", msg[0]);
		comMap.put("msg", msg[1]);
		
		return comMap;
	}
	
}
