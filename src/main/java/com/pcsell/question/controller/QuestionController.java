package com.pcsell.question.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.question.domain.Question;
import com.pcsell.question.repository.QuestionRepository;
import com.pcsell.user.domain.User;
import com.pcsell.util.HttpSessionUtils;

@Controller
@RequestMapping("question")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("")
	public String List(Model model, HttpSession session){
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}	
		model.addAttribute("questions", questionRepository.findAll());
		return "/question/list";
	}
	
	@GetMapping("/form")
	public String Form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		return "/question/form";
	}

	@PostMapping
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String View(@PathVariable Long id, Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}		
		model.addAttribute("question", questionRepository.findOne(id));
		return "/question/view";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}
		
		User logindUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(id);		
		if(!question.isSameWriter(logindUser)){
			return "redirect:/user/loginForm";
		}
		
		model.addAttribute("question", question);		
		return "/question/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session) {			
		User logindUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(id);			
		if(!question.isSameWriter(logindUser)){
			return "redirect:/user/loginForm";
		}
		
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/question/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session){
		User logindUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(id);		
		if(!question.isSameWriter(logindUser)){
			return "redirect:/user/loginForm";
		}
		
		questionRepository.delete(id);
		return "redirect:/";
	}
}
