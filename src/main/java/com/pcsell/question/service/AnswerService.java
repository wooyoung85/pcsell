package com.pcsell.question.service;

import java.util.List;

import com.pcsell.question.domain.Answer;

public interface AnswerService {
	public abstract List<Answer> list();

	public abstract boolean delete(Long id);

	public abstract void update(Long id, Answer updatedAnswer);

	public abstract Answer create(Answer answer);

	public abstract Answer findOne(Long id);
}
