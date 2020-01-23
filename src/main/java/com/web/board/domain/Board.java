package com.web.board.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="board")
public class Board implements Serializable{
	
	private static final long serialVersionUID = -7363117729121436953L;
	
	public Board(int listNo, String id, String name, Date createdAt, Date updatedAt, String contents, String title, String imgFilePath, String fileName, 
			String thumbImgFilePath, String thumbFileName, String orgFileName) {
		this.listNo = listNo;
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.contents = contents;
		this.title = title;
		this.imgFilePath = imgFilePath;
		this.fileName = fileName;
		this.thumbImgFilePath = thumbImgFilePath;
		this.thumbFileName = thumbFileName;
		this.orgFileName = orgFileName;
	}
	
	@Id	
	@Column(name = "listNo", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listNo;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="userKey", columnDefinition = "VARCHAR(100)", nullable = false)
	private User user;
	
	@Column(name = "id", nullable = false, length = 100)
	private String id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name="createdAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name="updatedAt", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name = "contents", nullable = false, columnDefinition = "MEDIUMTEXT")
	private String contents;
	
	@Column(name = "title", nullable = false, columnDefinition = "TEXT")
	private String title;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "passwordYn", nullable = false, columnDefinition = "VARCHAR(2) default 'N'")
	private String passwordYn;
	
	@Column(name = "viewYn", nullable = false, columnDefinition = "VARCHAR(2) default 'Y'")
	private String viewYn;
	
	@Column(name = "imgFilePath")
	private String imgFilePath;
	
	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "thumbImgFilePath")
	private String thumbImgFilePath;
	
	@Column(name = "thumbFileName")
	private String thumbFileName;
	
	@Column(name = "orgFileName")
	private String orgFileName;
}
