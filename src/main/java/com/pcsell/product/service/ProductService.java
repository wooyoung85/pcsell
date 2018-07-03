package com.pcsell.product.service;

import java.util.List;

import com.pcsell.product.domain.Product;

public interface ProductService {
	
	public abstract boolean create();
	
	public abstract List<Product> list();
	
	public abstract Product findOne(Long id);
	
	public abstract boolean update();
	
	public abstract boolean delete();
}


