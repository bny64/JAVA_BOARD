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

import com.web.auth.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="userStockList")
public class UserStockList implements Serializable{
	
	private static final long serialVersionUID = 7277902371115061789L;

	public UserStockList(String stockName, String stockCode, String stockNickName, String stockKey) {
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.stockNickName = stockNickName;
		this.stockKey = stockKey;
	}
	
	@Id
	@Column(name = "listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listNo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	@JoinColumn(name="userKey", columnDefinition = "VARCHAR(100)", nullable = false, unique = false)	
	private User user;
	
	@Column(name = "id", nullable = false, length = 100)
	private String id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "stockOrder", length = 30)
	private int stockOrder;
		
	@Column(name = "stockCode", nullable = false, length = 50)
	private String stockCode;
	
	@Column(name = "stockName", nullable = false, length = 50)
	private String stockName;
	
	@Column(name = "stockNickName", nullable = false, length = 50)
	private String stockNickName;
	
	@Column(name = "sellCheck", nullable = false, length = 5)
	private String sellCheck;
	
	@Column(name = "stockKey", nullable = false, length = 100)
	private String stockKey;
	
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
}
