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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @NoArgsConstructor : �Ķ���Ͱ� ���� �⺻ �����ڸ� ����
 * @AllArgsConstructor : ��� �ʵ� ���� �Ķ���ͷ� �޴� ������ ����
 * @RequiredArgsConstructor : final�̳� @NonNull�� �ʵ� ���� �Ķ���ͷ� �޴� �����ڸ� �����.
 * */
@Getter
@Setter
//hibernate���� multiselect�� ����� �� ����ϴ� �ʵ� ���� ����ϴ� �����ڸ� ������ ��.
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
