package com.web.auth.domain;

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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="userAuthority")
public class UserAuthority implements Serializable{
		
	private static final long serialVersionUID = 5931207130333253858L;
	
	@Id
	@Column(name = "listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listNo;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="userKey", nullable = false, columnDefinition = "VARCHAR(100)")
	private User user;
	
	@Column(name="id", nullable = false, unique=true, length=100)
	private String id;
	
	@Column(name="authority", columnDefinition = "VARCHAR(50) default 'ROLE_USER'")
	private String authority = "ROLE_USER";
	
	@Column(name="name", nullable = false, length=100)
	private String name;	
	
	@Column(name="enabled", columnDefinition = " VARCHAR(1) default 'Y'")
	private String enabled = "Y";
	
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@PrePersist
	public void prePersist() {
		this.authority = this.authority == null ? "ROLE_USER" : this.authority;
		this.enabled = this.enabled == null ? "Y" : this.enabled;
	}
	
}
