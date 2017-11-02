package com.pcsell.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.product.domain.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
