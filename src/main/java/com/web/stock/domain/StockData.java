package com.web.stock.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="stockData", schema = "tripleProject")
public class StockData implements Serializable{

	private static final long serialVersionUID = 259903335838245286L;

	@Id
	@Column(name="listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listNo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	@JoinColumn(name = "userKey", nullable = false, referencedColumnName = "userKey")	
	private UserStockList userStockList;
		
	@Column(name = "stockCode", nullable = false, length=50)
	private String stockCode;
		
	@Column(name = "stockName", nullable = false, length=50)
	private String stockName;
	
	@Column(name = "id", nullable = false, length = 100)
	private String id;
	
	@Column(name="stockDate", length = 50) //날짜
	private String stockDate;

	@Column(name="nowPrc", length = 50) //현재가
	private String nowPrc;
	
	@Column(name="byMnt", length = 50) //매수수량
	private String byMnt;
	
	@Column(name="accMnt", length = 50) //누적수량
	private String accMnt;
	
	@Column(name="ivstPrc", length = 50) //투자액
	private String ivstPrc;
	
	@Column(name="accIvstPrc", length = 50) //누적투자액
	private String accIvstPrc;
	
	@Column(name="buySrvfee", length = 50) //매수 수수료
	private String buySrvfee;
	
	@Column(name="sellSrvfee", length = 50) //매도 수수료
	private String srvfee;
	
	@Column(name="taxFee", length = 50) //세금
	private String taxFee;
	
	@Column(name="accEstPrc", length = 50) //누적평가액
	private String accEstPrc;
	
	@Column(name="ernRatePer", length = 50) //수익률(백분위)
	private String ernRatePer;
	
	@Column(name="ernRate", length = 50) //수익률
	private String ernRate;
	
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	
	
}
