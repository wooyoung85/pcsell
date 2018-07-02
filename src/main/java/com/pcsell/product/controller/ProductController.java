package com.pcsell.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.product.repository.ProductCategoryRepository;
import com.pcsell.product.repository.ProductRepository;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("products", productRepository.findAll());
		System.out.println(productRepository.findAll());
		return "/product/list";
	}
	
	@GetMapping("/{id}")
	public String view(@PathVariable Long id, Model model){
		model.addAttribute("product", productRepository.findById(id).get());		
		model.addAttribute("productCategoryList", productCategoryRepository.findAll());
	    return "/product/view";
	}
}
