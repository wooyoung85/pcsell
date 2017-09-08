package com.pcsell.question.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pcsell.user.domain.User;

import lombok.Data;

@Entity
@Data
public class Question {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	private String title;
	private String contents;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Question() {
	}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = new Date();
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;		
	}
}
