package com.pcsell.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.question.domain.Question;
import com.pcsell.user.domain.User;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findByWriter(User user);
}
