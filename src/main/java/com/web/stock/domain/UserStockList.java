package com.web.stock.domain;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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

	@Id
	@Column(name = "listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listNo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	@JoinColumn(name="userKey", columnDefinition = "VARCHAR(100)", nullable = false)	
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
	
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Transient
	@OneToMany(mappedBy = "stockData",fetch = FetchType.EAGER)
	private Collection<StockData> stockData;
	
}
