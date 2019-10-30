package com.web.user.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.web.log.domain.LoginLog;

import lombok.Data;

@Data
@Entity //@Entity : JPA���� ���̺� ������ Ŭ������ ���δ�.
@Table(name="user", schema = "tripleProject") //@Table(name='������ ���̺��') ->��ƼƼ�� ������ ���̺�� ����, @Table(schema='��Ű����') -> ��ƼƼ�� ��Ű����(default schema�� �����Ǿ� �����Ƿ�  �ȳ־��.)
public class User implements Serializable{
	
	@Id //@id : �⺻Ű
	@Column(name="userKey", nullable = false, columnDefinition = "VARCHAR(255)")
	//@Column(name='���̺��� �÷���')->���̺��� �÷���, @Column(nullable=boolean)->�� ����, @Column(columnDefinition='')->��Ÿ ����
	private String userKey;
	
	@Column(name = "id", nullable = false, unique = true, length = 100) //@Column(unique=boolean)-> ������ ����, @Column(length='number')->������ ����
	private String id;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "createdAt", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP) //��¥ Ÿ��(Date, Calendar)���ν� ���(DATE, TIME, TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "updatedAt", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP") //@Column(updatable, insertable=false) �б� ���� ���� 
	@Temporal(TemporalType.TIMESTAMP) 
	private Date updatedAt;
	
	@Column(name = "joinType", nullable = false, length = 20)
	private String joinType;
	
	@Column(name = "phoneNumber", length = 100)	
	private String phoneNumber;
	
	@Column(name = "imgPath")
	private String imgPath;
	
	@Column(name = "emailYn", nullable = false, columnDefinition = "VARCHAR(5) default 'N'") //columnDefinition���� default�� ������ TYPE�� �־����� ������ ���� �߻�.
	private String emailYn;
	
	@Column(name = "birth")	
	private Date birth;
	
	@Column(name = "introduce", columnDefinition = "TEXT")
	private String introduce;
	
	@OneToMany(mappedBy = "user")
	private Collection<LoginLog> loginLog;
}
