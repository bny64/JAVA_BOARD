package com.web.log.domain;

import java.io.Serializable;
import java.util.Date;

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

import com.web.user.domain.User;

import lombok.Data;

@Data
@Entity
@Table(name="loginLog")
public class LoginLog implements Serializable{
	
	@Id
	@Column(name="listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * GeneratedValue
	 * AUTO : JPA구현체가 자동으로 생성 전략을 결정한다.
	 * IDENTITY : 기본키 생성을 데이터베이스에 위임한다.
	 * SEQUENCE : 데이터베이스의 특별한 오브젝트 시퀀스를 사용하여 기본키를 생성한다.
	 * TABLE : 데이터베이스에 키 생성 전용 테이블을 만들고 이를 사용하여 기본키를 생성한다.
	 * */
	private int listNo;
	
	/*
	 * @Column(name="id", nullable = false, length=100) private String id;
	 */
	
	@Column(name="name", nullable = false, length=100)
	private String name;
	
	@Column(name="joinType", nullable = false, length = 20)
	private String joinType;
	
	@Column(name="loginAt", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	private User user;	
}
