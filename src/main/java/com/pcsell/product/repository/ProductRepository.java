package com.pcsell.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
