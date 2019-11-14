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

import com.web.auth.domain.User;

import lombok.Data;

@Data
@Entity
@Table(name="loginLog")
public class LoginLog implements Serializable{
	
	@Id
	@Column(name="listNo", nullable = false)
	/*
	 * GeneratedValue
	 * AUTO : JPA구현체가 자동으로 생성 전략을 결정한다.
	 * IDENTITY : 기본키 생성을 데이터베이스에 위임한다.
	 * SEQUENCE : 데이터베이스의 특별한 오브젝트 시퀀스를 사용하여 기본키를 생성한다.
	 * TABLE : 데이터베이스에 키 생성 전용 테이블을 만들고 이를 사용하여 기본키를 생성한다.
	 * */
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int listNo;
	
	@Column(name="name", nullable = false, length=100)
	private String name;
	
	@Column(name="joinType", nullable = false, length = 20)
	private String joinType;
	
	@Column(name="loginAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginAt;

	/*
	 * 1:N관계
	 * @ManyToOne(fetch = '') 엔티티 로딩 관련
	 * FetchType.LAZY : 엔티티를 조회할 때 연관된 엔티티를 실제 사용할 때 조회한다. 
	 * FetchType.EAGER : 엔티티를 조회할 때 연관된 엔티티도 함께 조회한다.
	 * @ManytoOne(optional='')
	 * default : true -> 반드시 값이 필요함
	 * false -> 해당 객체에 null이 들어갈 수도 있음.
	 * */
	@ManyToOne(fetch = FetchType.LAZY)
	/*
	 * @JoinColumn(name='N테이블의 기본키 이름')
	 * */		
	@JoinColumn(name="id")
	private User user;	
}
