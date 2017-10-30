package com.pcsell.question.service;

import java.util.List;

import com.pcsell.question.domain.Question;

public interface QuestionService {
	public abstract List<Question> list();

	public abstract boolean delete(Long id);

	public abstract void update(Long id, Question updatedQuestion);

	public abstract void create(Question question);

	public abstract Question findOne(Long id);
}
