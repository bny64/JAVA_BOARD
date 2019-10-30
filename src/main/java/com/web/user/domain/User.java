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
@Entity //@Entity : JPA에서 테이블에 매핑할 클래스에 붙인다.
@Table(name="user", schema = "tripleProject") //@Table(name='매핑할 테이블명') ->엔티티와 매핑할 테이블명 지정, @Table(schema='스키마명') -> 엔티티의 스키마명(default schema가 설정되어 있으므로  안넣어도됨.)
public class User implements Serializable{
	
	@Id //@id : 기본키
	@Column(name="userKey", nullable = false, columnDefinition = "VARCHAR(255)")
	//@Column(name='테이블의 컬럼명')->테이블의 컬럼명, @Column(nullable=boolean)->널 여부, @Column(columnDefinition='')->기타 정의
	private String userKey;
	
	@Column(name = "id", nullable = false, unique = true, length = 100) //@Column(unique=boolean)-> 고유값 설정, @Column(length='number')->데이터 길이
	private String id;
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "createdAt", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP) //날짜 타입(Date, Calendar)매핑시 사용(DATE, TIME, TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "updatedAt", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP") //@Column(updatable, insertable=false) 읽기 전용 변경 
	@Temporal(TemporalType.TIMESTAMP) 
	private Date updatedAt;
	
	@Column(name = "joinType", nullable = false, length = 20)
	private String joinType;
	
	@Column(name = "phoneNumber", length = 100)	
	private String phoneNumber;
	
	@Column(name = "imgPath")
	private String imgPath;
	
	@Column(name = "emailYn", nullable = false, columnDefinition = "VARCHAR(5) default 'N'") //columnDefinition에서 default값 설정시 TYPE을 넣어주지 않으면 에러 발생.
	private String emailYn;
	
	@Column(name = "birth")	
	private Date birth;
	
	@Column(name = "introduce", columnDefinition = "TEXT")
	private String introduce;
	
	@OneToMany(mappedBy = "user")
	private Collection<LoginLog> loginLog;
}
