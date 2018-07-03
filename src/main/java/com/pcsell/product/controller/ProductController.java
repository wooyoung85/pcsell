package com.pcsell.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcsell.product.service.ProductCategoryService;
import com.pcsell.product.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductCategoryService productCategoryService;

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("products", productService.list());
		return "/product/list";
	}

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.findOne(id));
		model.addAttribute("productCategoryList", productCategoryService.list());
		return "/product/view";
	}
}
