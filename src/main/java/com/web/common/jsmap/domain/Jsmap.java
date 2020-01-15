package com.web.common.jsmap.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @NoArgsConstructor : 파라미터가 없는 기본 생성자를 생성
 * @AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자 생성
 * @RequiredArgsConstructor : final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만든다.
 * */
@Getter
@Setter
//hibernate에서 multiselect를 사용할 때 사용하는 필드 값만 사용하는 생성자를 만들어야 함.
@RequiredArgsConstructor
@Entity
@Table(name = "js_map")
public class Jsmap implements Serializable{

	private static final long serialVersionUID = -813744718583406713L;
		
	@Id
	@Column(name="seq", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@NonNull
	@Column(name="url", nullable = false)
	private String url;
	
	@NonNull
	@Column(name="type", nullable = false)
	private String type;
		
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
}