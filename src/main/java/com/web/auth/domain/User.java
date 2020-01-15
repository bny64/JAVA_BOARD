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
/* @Entity : JPA에서 테이블에 매핑할 클래스에 붙인다.
 * */
@Entity
/* @Table(name='매핑할 테이블명') ->엔티티와 매핑할 테이블명 지정
 * @Table(schema='스키마명') -> 엔티티의 스키마명(default schema가 설정되어 있으므로  안넣어도됨.) * */
@Table(name="user", schema = "tripleProject")
public class User implements Serializable{
	
	private static final long serialVersionUID = -8112108611538016303L;
	
	public User() {		
	};
	
	public User(String id, String email, String password, String name, String joinType, String userType, String userKey) {
		//super();
		this.id = id;		
		this.email = email;
		this.password = password;
		this.name = name;
		this.joinType = joinType;
		this.userType = userType;
		this.userKey = userKey;
	}

	/* @id : 기본키
	 * */
	@Id
	/* @Column(name='테이블의 컬럼명')->테이블의 컬럼명
	 * @Column(nullable=boolean)->널 여부
	 * @Column(columnDefinition='')->기타 정의 
	 * */
	@Column(name="userKey", nullable = false, columnDefinition = "VARCHAR(255)")
	private String userKey;
	
	/* @Column(unique=boolean)-> 고유값 설정
	 * @Column(length='number')->데이터 길이
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
	/* 날짜 타입(Date, Calendar)매핑시 사용(DATE, TIME, TIMESTAMP)
	 * */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	/* @Column(updatable, insertable=false) 읽기 전용 변경 
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
	
	/* columnDefinition에서 default값 설정시 TYPE(ex : VARCHAR(5)을 넣어주지 않으면 에러 발생.
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
	
	/* 1:N 관계
	 * @OneToMany(mappedBy='') N테이블 에서의 해당 객체 변수명.
	 * @Transient DB테이블에는 존재하지 않지만 엔티티 클래스에는 등록되어 같이 운용해야 할 경우 사용
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
	 * @PrePersist 처음 저장시에만 호출
	 * @PreUpdate 저장 및 업데이트시 호출
	 */
	@PrePersist
	public void prePersist() {
		this.joinType = this.joinType == null ? "JAVA" : this.joinType;
		this.emailYn = this.emailYn == null ? "Y" : this.emailYn;
		this.userType = this.userType == null ? "B" : this.userType;
		this.useYn = this.useYn == null ? "Y" : this.useYn;
	}
}