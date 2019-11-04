package com.web.auth.domain;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor 
@Getter 
@Setter 
@ToString(exclude = {"password"})
/* @Entity : JPA에서 테이블에 매핑할 클래스에 붙인다.
 * */
@Entity
/* @Table(name='매핑할 테이블명') ->엔티티와 매핑할 테이블명 지정
 * @Table(schema='스키마명') -> 엔티티의 스키마명(default schema가 설정되어 있으므로  안넣어도됨.) * */
@Table(name="user", schema = "tripleProject")
public class User implements Serializable{
	
	private static final long serialVersionUID = -8112108611538016303L;

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
	
	@Column(name = "joinType", nullable = false, length = 20)
	private String joinType;
	
	@Column(name = "phoneNumber", length = 100)	
	private String phoneNumber;
	
	@Column(name = "imgPath")
	private String imgPath;
	
	/* columnDefinition에서 default값 설정시 TYPE을 넣어주지 않으면 에러 발생.
	 * */
	@Column(name = "emailYn", columnDefinition = "VARCHAR(5) default 'N'")
	private String emailYn;
	
	@Column(name = "birth")	
	private Date birth;
	
	@Column(name = "introduce", columnDefinition = "TEXT")
	private String introduce;
	
	@Column(name = "userType", nullable = false, columnDefinition = "VARCHAR(1) default 'D'")
	private String userType;
	
	/* 1:N 관계
	 * @OneToMany(mappedBy='') N테이블 에서의 해당 객체 변수명.
	 * */	
	@OneToMany(mappedBy = "user")
	private Collection<LoginLog> loginLog;
}
