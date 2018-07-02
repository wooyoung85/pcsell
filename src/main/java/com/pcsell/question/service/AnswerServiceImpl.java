package com.pcsell.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcsell.question.domain.Answer;
import com.pcsell.question.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Override
	public List<Answer> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		Answer answer = answerRepository.findById(id).get();
		if(answer == null){
			return false;
		}
		answerRepository.delete(answer);
		return true;
	}

	@Override
	public void update(Long id, Answer updatedAnswer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Answer create(Answer answer) {
		return answerRepository.save(answer);
	}

	@Override
	public Answer findOne(Long id) {		
		return answerRepository.findById(id).get();
	}

}
