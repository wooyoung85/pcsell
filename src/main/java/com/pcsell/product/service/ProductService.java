package com.pcsell.product.service;

import java.util.List;

import com.pcsell.product.domain.Product;

public interface ProductService {
	
	public abstract boolean createProduct();
	
	public abstract List<Product> retrieveProduct();
	
	public abstract boolean updateProduct();
	
	public abstract boolean deleteProduct();
}


