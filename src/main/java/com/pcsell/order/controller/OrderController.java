package com.pcsell.order.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.order.domain.Order;
import com.pcsell.order.repository.OrderRepository;
import com.pcsell.product.domain.Product;
import com.pcsell.user.domain.Result;
import com.pcsell.user.domain.User;
import com.pcsell.util.HttpSessionUtils;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping("")
	public String List(Model model, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		
		model.addAttribute("orderlist", orderRepository.findAll());
		
		return "/user/history";
	}
	
	@PostMapping
	public String Order(Product product, HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Order newOrder = new Order(sessionedUser, product);
		orderRepository.save(newOrder);
		return "/user/history";
	}
	
	private Result valid(HttpSession session, Order order){
		
		if(!HttpSessionUtils.isLoginUser(session)){
			return Result.fail("로그인이 필요합니다.");
		}
		
		User logindUser = HttpSessionUtils.getUserFromSession(session);
		
		if(orderRepository.findByCancel(logindUser) < 1){
			return Result.fail("신청된 주문이 없습니다.");
		}
		
		return Result.ok();
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, Model model, HttpSession session) {
	
		Order order = orderRepository.findOne(id);
		Result validationResult = valid(session, order);
		
		if(!validationResult.isValid()){
			model.addAttribute("errorMessage", validationResult.getErrorMessage());
			return "/user/login";
		}
		
		order.Cancel();
		orderRepository.save(order);
		
		return "/user/history";
	}

}
