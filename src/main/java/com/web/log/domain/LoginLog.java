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
	 * AUTO : JPA����ü�� �ڵ����� ���� ������ �����Ѵ�.
	 * IDENTITY : �⺻Ű ������ �����ͺ��̽��� �����Ѵ�.
	 * SEQUENCE : �����ͺ��̽��� Ư���� ������Ʈ �������� ����Ͽ� �⺻Ű�� �����Ѵ�.
	 * TABLE : �����ͺ��̽��� Ű ���� ���� ���̺��� ����� �̸� ����Ͽ� �⺻Ű�� �����Ѵ�.
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
