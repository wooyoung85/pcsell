package com.pcsell.product.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ProductCategory {
	@Id
	@GeneratedValue
	private Long id;		
	private String productCategoryName;
}
