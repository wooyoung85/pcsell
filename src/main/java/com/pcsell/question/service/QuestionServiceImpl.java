package com.pcsell.question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcsell.question.domain.Question;
import com.pcsell.question.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question findOne(Long id) {		
		return questionRepository.findById(id).get();
	}

	@Override
	public List<Question> list() {		
		return questionRepository.findAll();
	}

	@Override
	public boolean delete(Long id) {
		Question question = questionRepository.findById(id).get();
		if(question == null){
			return false;
		}
		questionRepository.delete(question);
		return true;
	}

	@Override
	public void update(Long id, Question updatedQuestion) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void create(Question question) {
		questionRepository.save(question);
	}
}
