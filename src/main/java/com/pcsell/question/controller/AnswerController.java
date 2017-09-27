package com.pcsell.question.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.question.domain.Answer;
import com.pcsell.question.domain.Question;
import com.pcsell.question.repository.AnswerRepository;
import com.pcsell.question.repository.QuestionRepository;
import com.pcsell.user.domain.User;
import com.pcsell.util.HttpSessionUtils;

@Controller
@RequestMapping("/question/{questionId}/answer")
public class AnswerController {
	@Autowired
	AnswerRepository answerRepository;
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping
	public String Create(@PathVariable Long questionId, String contents, HttpSession session){
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(loginUser, question, contents);
		answerRepository.save(answer);
		return String.format("redirect:/question/%d", questionId);
	}
}
