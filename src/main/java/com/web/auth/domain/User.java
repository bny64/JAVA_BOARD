package com.web.auth.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.web.board.domain.Board;
import com.web.board.domain.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
//@ToString(exclude = {"password"})
/* @Entity : JPA���� ���̺� ������ Ŭ������ ���δ�.
 * */
@Entity
/* @Table(name='������ ���̺��') ->��ƼƼ�� ������ ���̺�� ����
 * @Table(schema='��Ű����') -> ��ƼƼ�� ��Ű����(default schema�� �����Ǿ� �����Ƿ�  �ȳ־��.) * */
@Table(name="user", schema = "tripleProject")
public class User implements Serializable{
	
	private static final long serialVersionUID = -8112108611538016303L;
	
	public User() {		
	};
	
	public User(String id, String email, String password, String name, String joinType, String userType, String userKey) {
		super();
		this.id = id;		
		this.email = email;
		this.password = password;
		this.name = name;
		this.joinType = joinType;
		this.userType = userType;
		this.userKey = userKey;
	}

	/* @id : �⺻Ű
	 * */
	@Id
	/* @Column(name='���̺��� �÷���')->���̺��� �÷���
	 * @Column(nullable=boolean)->�� ����
	 * @Column(columnDefinition='')->��Ÿ ���� 
	 * */
	@Column(name="userKey", nullable = false, columnDefinition = "VARCHAR(255)")
	private String userKey;
	
	/* @Column(unique=boolean)-> ������ ����
	 * @Column(length='number')->������ ����
	 * */
	@Column(name = "id", nullable = false, unique = true, length = 100)
	private String id;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	/* ��¥ Ÿ��(Date, Calendar)���ν� ���(DATE, TIME, TIMESTAMP)
	 * */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	/* @Column(updatable, insertable=false) �б� ���� ���� 
	 * */
	@Column(name = "updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date updatedAt;
	
	@Column(name = "joinType", nullable = false, columnDefinition = "VARCHAR(20) default 'JAVA'")
	private String joinType;
	
	@Column(name = "phoneNumber", length = 100)	
	private String phoneNumber;
	
	@Column(name = "imgPath")
	private String imgPath;
	
	/* columnDefinition���� default�� ������ TYPE(ex : VARCHAR(5)�� �־����� ������ ���� �߻�.
	 * */
	@Column(name = "emailYn", columnDefinition = "VARCHAR(5) default 'N'")
	private String emailYn;
	
	@Column(name = "birth")	
	private Date birth;
	
	@Column(name = "introduce", columnDefinition = "TEXT")
	private String introduce;
	
	@Column(name = "userType", nullable = false, columnDefinition = "VARCHAR(1) default 'B'")
	private String userType;
	
	@Column(name = "useYn", columnDefinition = "VARCHAR(10) default 'Y'")
	private String useYn;
	
	@Column(name = "loginFailCnt", length = 10)
	private int loginFailCnt;
	
	/* 1:N ����
	 * @OneToMany(mappedBy='') N���̺� ������ �ش� ��ü ������.
	 * @Transient DB���̺��� �������� ������ ��ƼƼ Ŭ�������� ��ϵǾ� ���� ����ؾ� �� ��� ���
	 * */
	@Transient
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private Collection<Board> board;
	
	@Transient
	@OneToMany(mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Comment> comment;

	@Transient
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private UserAuthority userAuthority;
	
	/*
	 * @PrePersist ó�� ����ÿ��� ȣ��
	 * @PreUpdate ���� �� ������Ʈ�� ȣ��
	 */
	@PrePersist
	public void prePersist() {
		this.joinType = this.joinType == null ? "JAVA" : this.joinType;
		this.emailYn = this.emailYn == null ? "Y" : this.emailYn;
		this.userType = this.userType == null ? "B" : this.userType;
		this.useYn = this.useYn == null ? "Y" : this.useYn;
	}
}
