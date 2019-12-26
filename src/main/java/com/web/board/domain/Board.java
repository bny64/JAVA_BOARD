package com.web.board.domain;

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
import javax.persistence.Transient;

import com.web.auth.domain.User;

import lombok.Data;

@Data
@Entity
@Table(name="board")
public class Board implements Serializable{
	
	private static final long serialVersionUID = -7363117729121436953L;
	
	@Id	
	@Column(name = "listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listNo;
	
	@Transient
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="userKey", columnDefinition = "VARCHAR(100)", nullable = false)
	private User user;
	
	@Column(name = "id", nullable = false, length = 100)
	private String id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name = "contents", nullable = false, columnDefinition = "TEXT")
	private String contents;
	
	@Column(name = "title", nullable = false, columnDefinition = "TEXT")
	private String title;
	
	@Column(name = "password", length=100)
	private String password;
	
	@Column(name = "passwordYn", nullable = false, columnDefinition = "VARCHAR(2) default 'N'")
	private String passwordYn;
	
	@Column(name = "viewYn", nullable = false, columnDefinition = "VARCHAR(2) default 'Y'")
	private String viewYn;		
}
