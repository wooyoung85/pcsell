package com.pcsell.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcsell.product.domain.ProductCategory;
import com.pcsell.product.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository; 
	
	@Override
	public List<ProductCategory> list() {
		return productCategoryRepository.findAll();
	}

}
