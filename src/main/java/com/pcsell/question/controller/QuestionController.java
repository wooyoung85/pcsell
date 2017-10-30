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
import com.pcsell.question.service.QuestionService;
import com.pcsell.user.domain.Result;
import com.pcsell.user.domain.User;
import com.pcsell.util.HttpSessionUtils;

@Controller
@RequestMapping("question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@GetMapping("")
	public String List(Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		model.addAttribute("questions", questionService.list());
		return "/question/list";
	}

	@GetMapping("/form")
	public String Form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		return "/question/form";
	}

	@PostMapping
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		System.out.println("contents : "+contents);
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser, title, contents);
		questionService.create(newQuestion);
		return String.format("redirect:/question/%d", newQuestion.getId());
	}

	@GetMapping("/{id}")
	public String View(@PathVariable Long id, Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		model.addAttribute("question", questionService.findOne(id));
		return "/question/view";
	}

	private Result valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인이 필요합니다.");
		}
		User logindUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(logindUser)) {
			return Result.fail("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
		return Result.ok();
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionService.findOne(id);
		Result validationResult = valid(session, question);
		if (!validationResult.isValid()) {
			model.addAttribute("errorMessage", validationResult.getErrorMessage());
			return "/user/login";
		}
		model.addAttribute("question", question);
		return "/question/updateForm";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		Question question = questionService.findOne(id);
		Result validationResult = valid(session, question);
		if (!validationResult.isValid()) {
			model.addAttribute("errorMessage", validationResult.getErrorMessage());
			return "/user/login";
		}
		System.out.println("contents : "+contents);
		question.update(title, contents);
		questionService.create(question);
		return String.format("redirect:/question/%d", id);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionService.findOne(id);
		Result validationResult = valid(session, question);
		if (!validationResult.isValid()) {
			model.addAttribute("errorMessage", validationResult.getErrorMessage());
			return "/user/login";
		}
		questionService.delete(id);
		return "redirect:/";
	}
}
