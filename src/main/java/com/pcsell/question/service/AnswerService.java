package com.pcsell.question.service;

import java.util.List;

import com.pcsell.question.domain.Answer;

public interface AnswerService {
	public abstract List<Answer> list();

	public abstract boolean delete(Long id);

	public abstract void update(Long id, Answer updatedAnswer);

	public abstract void create(Answer answer);

	public abstract Answer view(Long id);
}
