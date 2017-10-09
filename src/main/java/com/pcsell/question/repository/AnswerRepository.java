package com.pcsell.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.question.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

}
