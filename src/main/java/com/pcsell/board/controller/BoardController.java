package com.pcsell.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.board.domain.Board;
import com.pcsell.board.repository.BoardRepository;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;

	@GetMapping("/form")
	public String Form() {
		return "/board/form";
	}

	@PostMapping("")
	public String Create(Board board) {		
		boardRepository.save(board);
		return "redirect:/board";
	}

	@GetMapping("")
	public String List(Model model) {
		model.addAttribute("board", boardRepository.findAll());
		return "/board/list";
	}

	/*@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user/updateform";
	}

	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User updateUser) {
		User user = userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/loginForm")
	public String LoginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String Login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if(user == null || !password.equals(user.getPassword())){
			System.out.println("로그인 실패");
			return "redirect:/users/loginForm";
		}
		
		session.setAttribute("user", user);
		
		return "redirect:/";
	}*/
}
