package com.pcsell.board.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity(name = "board")
@Data
public class Board {
	@Id
	@GeneratedValue
	private int seq;
	@Length(min=2, message="제목은 2자 이상 입력하세요.")
	private String title;
	@NotEmpty(message = "내용을 입력하세요.")
	private String content;
	@NotEmpty(message = "작성자를 입력하세요.")
	private String writer;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	private int cnt;
	
	public Board() { }

	public Board(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;			
		this.cnt = 0;
	}
}
