package com.pcsell.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.user.domain.User;
import com.pcsell.user.repository.UserRepository;
import com.pcsell.user.service.UserService;
import com.pcsell.util.HttpSessionUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/form")
	public String Form() {
		return "/user/form";
	}

	@PostMapping("")
	public String Create(String userId, String password, String name, String email) {
		User user = new User(userId, password, name, email);
		userService.create(user);
		return "redirect:/user";
	}

	@GetMapping("")
	public String List(Model model) {
		model.addAttribute("users", userService.list());
		return "/user/list";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/loginForm";
		}			
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)){
			throw new IllegalStateException("잘못된 접근입니다.");
		}
			
		User user = userRepository.findOne(id);		
		model.addAttribute("user", user);
		return "/user/updateform";
	}

	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User updateUser, HttpSession session) {		
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/user/loginForm";
		}			
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if(!sessionedUser.matchId(id)){
			throw new IllegalStateException("잘못된 접근입니다.");
		}
		
		userService.update(id, updateUser);
		return "redirect:/user";
	}

	@GetMapping("/loginForm")
	public String LoginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String Login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		
		if(user == null || !password.equals(user.getPassword())){
			System.out.println("로그인에 실패했습니다.");
			return "redirect:/user/loginForm";
		}		
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String LogOut(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
}
