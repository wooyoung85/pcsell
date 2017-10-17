package com.pcsell.question.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcsell.question.domain.Answer;
import com.pcsell.question.domain.Question;
import com.pcsell.question.repository.AnswerRepository;
import com.pcsell.question.repository.QuestionRepository;
import com.pcsell.user.domain.User;
import com.pcsell.user.domain.Result;
import com.pcsell.util.HttpSessionUtils;

@RestController
@RequestMapping("/api/question/{questionId}")
public class ApiAnswerController {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	AnswerRepository answerRepository;
	
	@PostMapping("/answer")
	public Answer Create(@PathVariable Long questionId, String contents, HttpSession session) {
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(loginUser, question, contents);
		return answerRepository.save(answer);
	} 

	@PutMapping("/updateAnswer/{id}")
	public Answer Update(@PathVariable Long id, String contents, Model model, HttpSession session) {
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Answer answer = answerRepository.findOne(id);
		answer.update(loginUser, contents);
		return answerRepository.save(answer);
	}

	@DeleteMapping("/answer/{id}")
	public Result Delete(@PathVariable Long id, Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)){
			return Result.fail("로그인이 필요합니다.");
		}
		Answer answer = answerRepository.findOne(id);
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)){
			return Result.fail("자신의 글만 삭제할 수 있습니다.");
		}
		answerRepository.delete(id);
		return Result.ok();
	}
}
