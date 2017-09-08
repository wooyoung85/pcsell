package com.pcsell.question.service;

import java.util.List;

import com.pcsell.question.domain.Question;

public interface QuestionService {
	public abstract List<Question> list();

	public abstract boolean delete(Long id);

	public abstract void update(Long id, Question updatedUser);

	public abstract void create(Question user);

	public abstract Question view(Long id);
}
