package com.pcsell.question.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pcsell.user.domain.User;

import lombok.Data;

@Entity
@Data
public class Answer {
	@Id
	@GeneratedValue
	private Long Id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	@Lob
	private String contents;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	public Answer() {
	}
	
	public Answer(User loginUser, Question question, String contents) {
		this.writer = loginUser;
		this.question = question;
		this.contents = contents;
		this.createDate = new Date();
	}
	
	public void update(User writer, String contents) {
		this.writer = writer;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}
}
